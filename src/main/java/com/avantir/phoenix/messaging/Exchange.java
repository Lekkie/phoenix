package com.avantir.phoenix.messaging;

import com.avantir.phoenix.model.Node;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by lekanomotayo on 12/01/2018.
 */
public class Exchange {

    String isoPackagerName;
    boolean binaryBitmap;
    IsoMessage isoMessage;
    Node fromNode;
    ISO8583Node iso8583Node;
    ChannelHandlerContext ctx;

    public Exchange(){

    }

    public Exchange(String isoPackagerName, boolean binaryBitmap, IsoMessage isoMessage, Node fromNode, ISO8583Node iso8583Node, ChannelHandlerContext ctx){
        this.isoPackagerName = isoPackagerName;
        this.binaryBitmap = binaryBitmap;
        this.isoMessage = isoMessage;
        this.fromNode = fromNode;
        this.iso8583Node = iso8583Node;
        this.ctx = ctx;
    }

    public String getIsoPackagerName() {
        return isoPackagerName;
    }

    public void setIsoPackagerName(String isoPackagerName) {
        this.isoPackagerName = isoPackagerName;
    }

    public boolean isBinaryBitmap() {
        return binaryBitmap;
    }

    public void setBinaryBitmap(boolean binaryBitmap) {
        this.binaryBitmap = binaryBitmap;
    }

    public IsoMessage getIsoMessage() {
        return isoMessage;
    }

    public void setIsoMessage(IsoMessage isoMessage) {
        this.isoMessage = isoMessage;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public ISO8583Node getIso8583Node() {
        return iso8583Node;
    }

    public void setIso8583Node(ISO8583Node iso8583Node) {
        this.iso8583Node = iso8583Node;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
}
