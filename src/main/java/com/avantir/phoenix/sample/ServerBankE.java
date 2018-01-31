package com.avantir.phoenix.sample;

import com.avantir.phoenix.messaging.EndpointStarter;
import com.avantir.phoenix.messaging.ISO8583Node;
import com.avantir.phoenix.utils.IsoUtil;
import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.server.Iso8583Server;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;
import io.netty.channel.ChannelHandlerContext;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Created by lekanomotayo on 02/01/2018.
 */
public class ServerBankE {


    public static void main(String[] args){

        try{
            int port = 60002;
            //MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();// [1]
            String xml = "/Users/lekanomotayo/projects/phoenix/src/main/resources/packagers/POST_PACKAGER.xml";
            String template = EndpointStarter.readFile(xml);
            StringReader reader = new StringReader(template);
            MessageFactory<IsoMessage> messageFactory = ConfigParser.createFromReader(reader);
            messageFactory.setUseBinaryBitmap(true);
            //messageFactory.setUseBinaryMessages(true);
            //messageFactory.setCharacterEncoding(StandardCharsets.US_ASCII.name());
            messageFactory.setCharacterEncoding(StandardCharsets.UTF_8.name());

            Iso8583Server<IsoMessage> server = new Iso8583Server<>(port, messageFactory);// [2]
            server.addMessageListener(new IsoMessageListener<IsoMessage>() { // [3]
                @Override
                public boolean applies(IsoMessage isoMessage) {
                    return true;
                }

                @Override
                public boolean onMessage(ChannelHandlerContext ctx, IsoMessage isoMessage) {
                    if(IsoUtil.isRequest(isoMessage)){
                        System.out.println("Incoming request to Server Bank A: " +isoMessage.toString());
                        final IsoMessage response = server.getIsoMessageFactory().createResponse(isoMessage);
                        response.setField(39, IsoType.ALPHA.value("00", 2));
                        //response.setField(60, IsoType.LLLVAR.value("XXX", 3));
                        ctx.writeAndFlush(response);
                    }
                    else{
                        System.out.println("Incoming response to Server Bank A: " + isoMessage.toString());
                    }
                    return false;
                }
            });
            //server.getConfiguration().replyOnError(true);// [4]
            server.init();// [5]

            server.start();// [6]
            if (server.isStarted()) { // [7]
                System.out.println("Server A on port " + port + " is started");
                TimeUnit.MINUTES.sleep(60);
            }

            server.shutdown();// [8]
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
