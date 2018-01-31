package com.avantir.phoenix.messaging;

import com.avantir.phoenix.isobridge.AInterchange;
import com.avantir.phoenix.model.ISOBridge;
import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.SAPEndpoint;
import com.avantir.phoenix.services.SwitchKeyOrigDataElemService;
import com.avantir.phoenix.tranmgr.TranMgr;
import com.avantir.phoenix.utils.IsoUtil;
import com.avantir.phoenix.utils.StringUtil;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

public class ISO8583SrcNode extends ISO8583Node {

    public ISO8583SrcNode(TranMgr tranMgr, SAPEndpoint sapEndpoint, Node node, ISOBridge isoBridge, IConnection connection)throws Exception{

        if(!connection.isServer())
            throw new Exception("Src node does not support Client connection at the moment");

        this.tranMgr = tranMgr;
        this.sapEndpoint = sapEndpoint;
        this.node=node;
        this.isoBridge = isoBridge;
        this.connection = connection;
        //ApplicationContext context = SpringContext.getApplicationContext();
        String className = StringUtil.lowerCaseFirst(isoBridge.getClassName());
        interchange = (AInterchange)context.getBean(className);
        switchKeyOrigDataElemService = (SwitchKeyOrigDataElemService)context.getBean("switchKeyOrigDataElemService");
    }


    //  Ideally, only request shld go thru here...
    // send receive request to remote = send request to TM
    public void receiveRequestFromRemote(ChannelHandlerContext ctx, IsoMessage isoMessage){

        IsoMessage messageToTranMgr = interchange.requestFromRemote(isoMessage); //call an interface bridge

        Long switchKey = IsoUtil.generateSwitchKey(isoMessage, switchKeyOrigDataElemService);
        if(switchKey == -1){
            IsoUtil.sendResponse(this, ctx, isoMessage, isoBridge.getIsoPackagerName(), isoBridge.isBinaryBitmap(), IsoUtil.RESP_94);
            return;
        }
        if(switchKey < 1){
            logger.error("receiveRequestFromRemote: Switch key cannot be less than 1");
            IsoUtil.sendResponse(this, ctx, isoMessage, isoBridge.getIsoPackagerName(), isoBridge.isBinaryBitmap(), IsoUtil.RESP_96);
            return;
        }

        // Set Switch Key
        IsoUtil.setCompositeChildValue(isoMessage, isoBridge.getIsoPackagerName(), isoBridge.isBinaryBitmap(), IsoUtil.f127, IsoUtil.f2, String.valueOf(switchKey));
        super.receiveMessage(ctx, messageToTranMgr);
    }

    //Ideally, only response shld go thru here...
    // send response to remote = receive response from TM
    public void sendResponseToRemote(ChannelHandlerContext ctx, IsoMessage isoMessage){
        IsoMessage messageToRemote = interchange.responseFromTranMgr(isoMessage); //call an interface bridge
        super.sendReply(ctx, messageToRemote);
    }

}
