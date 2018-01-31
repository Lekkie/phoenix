package com.avantir.phoenix.messaging;

import com.avantir.phoenix.utils.IsoUtil;
import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.solab.iso8583.IsoMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by lekanomotayo on 04/01/2018.
 */
public class SrcNodeListener  implements IsoMessageListener {

    ISO8583SrcNode iso8583SrcNode;

    public SrcNodeListener(ISO8583SrcNode iso8583SrcNode){
        this.iso8583SrcNode = iso8583SrcNode;
    }

    public boolean onMessage(ChannelHandlerContext ctx, IsoMessage isoMessage) {
        // new thread shld be handling this, so this main thread can return.
        iso8583SrcNode.receiveRequestFromRemote(ctx, isoMessage);
        return false;
    }

    @Override
    public boolean applies(IsoMessage isoMessage) {
        return IsoUtil.isRequest(isoMessage) && !IsoUtil.isEchoRequest(isoMessage);
    }

}
