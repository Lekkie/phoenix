package com.avantir.phoenix.tranmgr;

import com.avantir.phoenix.messaging.*;
import com.avantir.phoenix.model.*;
import com.avantir.phoenix.services.MessageTypeService;
import com.avantir.phoenix.services.NodeService;
import com.avantir.phoenix.services.TotalsGroupService;
import com.avantir.phoenix.services.TransactionTypeService;
import com.avantir.phoenix.utils.IsoUtil;
import com.avantir.phoenix.utils.StringUtil;
import com.solab.iso8583.IsoMessage;
//import com.solab.iso8583.IsoType;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

/**
 * Created by lekanomotayo on 04/01/2018.
 */

@Component
public class TranMgr {

    private static final Logger logger = LoggerFactory.getLogger(TranMgr.class);

    @Autowired
    Router router;
    @Autowired
    TotalsGroupService totalsGroupService;
    @Autowired
    NodeService nodeService;
    @Autowired
    MessageTypeService messageTypeService;
    @Autowired
    TransactionTypeService transactionTypeService;


    private static TreeMap<Long, SAPInterchange> sapInterchangeTreeMap = new TreeMap<Long, SAPInterchange>();
    private static TreeMap<Long, ISO8583SrcNode> iso8583SrcNodeTreeMap = new TreeMap<Long, ISO8583SrcNode>();
    private static TreeMap<Long, ISO8583SinkNode> iso8583SinkNodeTreeMap = new TreeMap<Long, ISO8583SinkNode>();
    private static TreeMap<String, SrcNodeInfo> srcNodeInfoTreeMap = new TreeMap<String, SrcNodeInfo>();


    /***
     * Valid Scenarios
     * Fresh/Originating Request coming to be routed to a sink node
     * Returning Response to a waiting Request to be routed to its waiting src node
     * Fresh Response coming to be routed to a sink node - drop
     * Returning Request to a waiting Request to be routed to its waiting srcnode - drop
     *
     * If message (ideally a request) is coming from a src node, it has to be routed to a sink node
     * Or may be, can it be routed to another src node? For now, no
     * If message (ideally a response) is coming from a sink node, it has to be routed to a src node
     *
     * validate
     * route request to sink node or route response to src node
     *
     * if it is coming from sink node, it is response, forward to a src node to send response out
     * if it is coming from src node, it is request, forward to a snk node to send request out
     *
     * When sending a request from src node to snk node connection,
     * ensure the snk node connection is a client...server sink node not supported yet
     *
     * When sending a response from snk node to src node connection,
     * ensure the src node connection is a server...client src node not supported yet
     *
     */
    public void process(Exchange requestExchange){

        String isoPackagerName = requestExchange.getIsoPackagerName();
        boolean binaryBitmap = requestExchange.isBinaryBitmap();
        IsoMessage isoMessage = requestExchange.getIsoMessage();
        Node fromNode = requestExchange.getFromNode();
        ISO8583Node iso8583Node = requestExchange.getIso8583Node();
        ChannelHandlerContext ctx = requestExchange.getCtx();

        int fromNodeType = fromNode.getType();
        if(fromNodeType == IsoUtil.SRC_NODE){
            ISO8583SrcNode iso8583SrcNode = (ISO8583SrcNode) iso8583Node;

            String messageTypeStr = String.format("%04X", isoMessage.getType());
            MessageType messageType = messageTypeService.findByCode(messageTypeStr);
            if(messageType == null || messageType.getId() < 1){
                logger.error("process: Message Type " + messageTypeStr + " cannot be found");
                IsoUtil.sendResponse(iso8583SrcNode, ctx, isoMessage, isoPackagerName, binaryBitmap, IsoUtil.RESP_40);
                return;
            }

            String tranType = IsoUtil.getTranType(isoMessage);
            TransactionType transactionType = transactionTypeService.findByCode(tranType);
            if(transactionType == null || transactionType.getId() < 1){
                logger.error("process: Transaction Type " + tranType + " cannot be found");
                IsoUtil.sendResponse(iso8583SrcNode, ctx, isoMessage, isoPackagerName, binaryBitmap, IsoUtil.RESP_40);
                return;
            }

            // Validate Request
            String pan = IsoUtil.getFieldValue(isoMessage, IsoUtil.f2);
            if(!IsoUtil.validatePan(pan)){
                logger.error("process: Invalid card");
                IsoUtil.sendResponse(iso8583SrcNode, ctx, isoMessage, isoPackagerName, binaryBitmap, IsoUtil.RESP_14);
                return;
            }

            if(fromNode.isValidateExpDate()){
                String expDate = IsoUtil.getFieldValue(isoMessage, IsoUtil.f14);
                if(!IsoUtil.validateExpDate(expDate)){
                    logger.error("process: Expiry Date");
                    IsoUtil.sendResponse(iso8583SrcNode, ctx, isoMessage, isoPackagerName, binaryBitmap, IsoUtil.RESP_54);
                    return;
                }
            }

            // this is request,route forward. find sink node, and forward request
            forwardToSinkNode(requestExchange);
        } else if(fromNodeType == IsoUtil.SINK_NODE){
            // Validate Response

            // this is response, route back. find src node, forward response
            ISO8583SinkNode iso8583SinkNode = (ISO8583SinkNode) iso8583Node;
            returnResponseToSrcNode(isoPackagerName, binaryBitmap, isoMessage, fromNode, iso8583SinkNode, ctx);
            return;

        } else{
            // Shld never happen, log error
            logger.error("process: Node type is neither Src nor Sink.");
        }
    }



    /**
     * Route to a sink node.
     * Routing rule/order
     *      1. Recv Instd Id + Routing grp (SrcNode + Card acceptor)
     *      2. Totals grp (By CardBIN + Acct type) +  Routing grp (SrcNode + Card acceptor)
     * if none found, return 92
     *
     */
    private void forwardToSinkNode(Exchange requestExchange){

        String isoPackagerName = requestExchange.getIsoPackagerName();
        boolean binaryBitmap = requestExchange.isBinaryBitmap();
        IsoMessage isoReq = requestExchange.getIsoMessage();
        Node srcNode = requestExchange.getFromNode();
        ISO8583SrcNode iso8583SrcNode = (ISO8583SrcNode) requestExchange.getIso8583Node();
        ChannelHandlerContext ctx = requestExchange.getCtx();

        Long sinkNodeId = 0L;
        ISOBridge isoBridge = iso8583SrcNode.getIsoBridge();
        if(isoBridge == null){
            logger.error("routeToSinkNode: Isobridge cannot be null");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_96);
            return;
        }

        String pan = IsoUtil.getFieldValue(isoReq, IsoUtil.f2);
        if(StringUtil.isEmpty(pan)){
            logger.error("routeToSinkNode: PAN cannot be null");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_96);
            return;
        }

        String fromAccTypeCode = IsoUtil.getFromAccountType(isoReq);
        if(StringUtil.isEmpty(fromAccTypeCode)){
            logger.error("routeToSinkNode: From Account Type cannot be null");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_96);
            return;
        }

        String cardAcceptorCode = IsoUtil.getFieldValue(isoReq, IsoUtil.f42);
        if(StringUtil.isEmpty(cardAcceptorCode)){
            logger.error("routeToSinkNode: Card Acceptor Code cannot be null");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_96);
            return;
        }

        String receivingInst = IsoUtil.getFieldValue(isoReq, IsoUtil.f100);

        Long totalsGroupId = router.getTotalsGroup(pan, fromAccTypeCode);
        if(totalsGroupId < 1){
            logger.error("routeToSinkNode: Totals Group Id cannot be less than 1");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_92);
            return;
        }

        TotalsGroup totalsGroup = totalsGroup = totalsGroupService.findById(totalsGroupId);
        if(totalsGroup == null){
            logger.error("routeToSinkNode: Totals Group cannot be null");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_92);
            return;
        }

        //Get f100 if present Get Sink node
        if(!StringUtil.isEmpty(receivingInst)){
            sinkNodeId = router.getRouteByReceivingInst(srcNode, cardAcceptorCode, receivingInst);
        }else{
            sinkNodeId = router.getRouteByCard(srcNode, cardAcceptorCode, totalsGroupId);
        }

        if(sinkNodeId < 1){
            logger.error("routeToSinkNode: Sink Node Id cannot be less than 1");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_92);
            return;
        }

        Node sinkNode = nodeService.findByNodeId(sinkNodeId);
        if(sinkNode == null){
            logger.error("routeToSinkNode: Sink Node cannot be null");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_92);
            return;
        }

        if(sinkNode.getType() == IsoUtil.SRC_NODE){
            logger.error("routeToSinkNode: Node cannot be a SrcNode");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_92);
            return;
        }

        ISO8583SinkNode iso8583SinkNode = getISO8583SinkNode(sinkNodeId);
        if(iso8583SinkNode == null){
            logger.error("routeToSinkNode: ISO8583 Sink Node cannot be null. This Sink node has no connection running");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_91);
            return;
        }

        IConnection connection = iso8583SinkNode.getConnection();
        if(connection == null){
            logger.error("routeToSinkNode: Sink Node Connection cannot be null. This Sink node has no connection running");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_91);
            return;
        }

        if(connection.isServer()){
            logger.error("routeToSinkNode: Sink Node Connection cannot be a server, it has to be a client");
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_96);
            return;
        }

        try{
            String routingInfo = IsoUtil.getRoutingInfo(srcNode, sinkNode, isoReq, totalsGroup);
            //update field 127.3
            isoReq = IsoUtil.setCompositeChildValue(isoReq, isoPackagerName, binaryBitmap, IsoUtil.f127, IsoUtil.f3, routingInfo);
            String switchKey = IsoUtil.getCompositeFieldValue(isoReq, IsoUtil.f127, IsoUtil.f2);
            SrcNodeInfo srcNodeInfo = new SrcNodeInfo();
            srcNodeInfo.setCtx(ctx);
            srcNodeInfo.setSrcNodeId(srcNode.getId());
            srcNodeInfoTreeMap.put(switchKey, srcNodeInfo);
            // Forward request to Sink Node
            iso8583SinkNode.sendRequestToRemote(connection, isoReq);
            return;
        }
        catch(Exception ex){
            logger.error("routeToSinkNode: An error occur while routing, see stacktrace", ex);
            ex.printStackTrace();
            // Return 96, System Malfunction
            IsoUtil.sendResponse(iso8583SrcNode, ctx, isoReq, isoPackagerName, binaryBitmap, IsoUtil.RESP_96);
        }
    }


    private void returnResponseToSrcNode(String isoPackagerName, boolean binaryBitmap, IsoMessage isoResp, Node sinkNode, ISO8583SinkNode iso8583SinkNode, ChannelHandlerContext ctx){

        String switchKey = IsoUtil.getCompositeFieldValue(isoResp, IsoUtil.f127, IsoUtil.f2);
        SrcNodeInfo srcNodeInfo = srcNodeInfoTreeMap.get(switchKey);
        if(srcNodeInfo == null){
            logger.error("returnResponseToSrcNode: Src Node Info for this message (Switch Key: " + switchKey + " cannot be found");
            return;
        }
        //find src node, forward response
        ISO8583SrcNode iso8583SrcNode = getISO8583SrcNode(srcNodeInfo.getSrcNodeId());
        if(srcNodeInfo == null){
            logger.error("returnResponseToSrcNode: Src Node Connection for this message (Switch Key: " + switchKey + " cannot be found");
            return;
        }

        iso8583SrcNode.sendResponseToRemote(srcNodeInfo.getCtx(), isoResp);
    }


    public static TreeMap<Long, SAPInterchange> getSapInterchanges(){
        return sapInterchangeTreeMap;
    }

    public static ISO8583SrcNode getISO8583SrcNode(Long srcNodeId){
        return iso8583SrcNodeTreeMap.get(srcNodeId);
    }

    public static ISO8583SinkNode getISO8583SinkNode(Long sinkNodeId){
        return iso8583SinkNodeTreeMap.get(sinkNodeId);
    }

    public static void addSinkNode(Long id, ISO8583SinkNode iso8583SinkNode){
        iso8583SinkNodeTreeMap.put(id, iso8583SinkNode);
    }

    public static void addSrcNode(Long id, ISO8583SrcNode iso8583SrcNode){
        iso8583SrcNodeTreeMap.put(id, iso8583SrcNode);
    }



}
