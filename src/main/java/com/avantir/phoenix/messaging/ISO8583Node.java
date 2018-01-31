package com.avantir.phoenix.messaging;

import com.avantir.phoenix.utils.SpringContext;
import com.avantir.phoenix.isobridge.Interchange;
import com.avantir.phoenix.model.ISOBridge;
import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.SAPEndpoint;
import com.avantir.phoenix.services.SwitchKeyOrigDataElemService;
import com.avantir.phoenix.tranmgr.TranMgr;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by lekanomotayo on 01/01/2018.
 */
public abstract class ISO8583Node {

    protected static final Logger logger = LoggerFactory.getLogger(ISO8583Node.class);

    protected TranMgr tranMgr;
    protected Interchange interchange;
    protected SAPEndpoint sapEndpoint;
    protected Node node;
    protected ISOBridge isoBridge;
    protected IConnection connection;
    protected SwitchKeyOrigDataElemService switchKeyOrigDataElemService;

    protected static ApplicationContext context = SpringContext.getApplicationContext();

    protected void receiveMessage(ChannelHandlerContext ctx, IsoMessage isoMessage){
        Exchange exchange = new Exchange(isoBridge.getIsoPackagerName(), isoBridge.isBinaryBitmap(), isoMessage, node, this, ctx);
        tranMgr.process(exchange);
    }

    protected void sendMessage(IConnection connection, IsoMessage isoMessage) throws Exception{
        connection.send(isoMessage);
    }

    protected void sendReply(ChannelHandlerContext ctx, IsoMessage isoMessage){
        ctx.writeAndFlush(isoMessage);
    }


    public IConnection getConnection() {
        return connection;
    }

    public ISOBridge getIsoBridge() {
        return isoBridge;
    }


}
