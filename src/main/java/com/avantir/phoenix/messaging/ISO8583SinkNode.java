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
public class ISO8583SinkNode extends ISO8583Node {

    public ISO8583SinkNode(TranMgr tranMgr, SAPEndpoint sapEndpoint, Node node, ISOBridge isoBridge, IConnection connection) throws Exception{

        if(connection.isServer())
            throw new Exception("Sink node does not support Server connection at the moment");

        this.tranMgr = tranMgr;
        this.sapEndpoint = sapEndpoint;
        this.node = node;
        this.isoBridge = isoBridge;
        this.connection = connection;
        //ApplicationContext context = SpringContext.getApplicationContext();
        String className = StringUtil.lowerCaseFirst(isoBridge.getClassName());
        interchange = (AInterchange)context.getBean(className);
        switchKeyOrigDataElemService = (SwitchKeyOrigDataElemService)context.getBean("switchKeyOrigDataElemService");
    }

    //Ideally, only request shld go thru here...
    // send request to remote = receive request from TM
    public void sendRequestToRemote(IConnection connection, IsoMessage isoMessage)throws Exception{
        IsoMessage messageToRemote = interchange.requestFromTranMgr(isoMessage); //call an interface bridge
        super.sendMessage(connection, messageToRemote);
    }

    //Ideally, only response shld go thru here...
    // receive response from remote = send response to TM
    public void receiveResponseFromRemote(ChannelHandlerContext ctx, IsoMessage isoMessage){
        IsoMessage messageToTranMgr = interchange.responseFromRemote(isoMessage); // call an interface bridge

        Long switchKey = IsoUtil.getSwitchKey(isoMessage, switchKeyOrigDataElemService);
        if(switchKey < 1) {
            logger.error("receiveResponseFromRemote: Switch key not found for this message");
            return;
        }
        IsoUtil.setCompositeChildValue(isoMessage, isoBridge.getIsoPackagerName(), isoBridge.isBinaryBitmap(), IsoUtil.f127, IsoUtil.f2, String.valueOf(switchKey));
        super.receiveMessage(ctx, messageToTranMgr); // receive response and forward to TM
    }
}
