package com.avantir.phoenix.sample;

import com.avantir.phoenix.messaging.EndpointStarter;
import com.avantir.phoenix.messaging.ISO8583Node;
import com.avantir.phoenix.utils.IsoUtil;
import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.solab.iso8583.*;
import com.solab.iso8583.parse.ConfigParser;
import io.netty.channel.ChannelHandlerContext;

import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Created by lekanomotayo on 02/01/2018.
 */
public class ClientBankB {


    public static int stan = 19;



    public static void main(String[] args){

        try{
            int port = 5043; //50000
            String host = "196.6.103.72"; //"192.168.1.66"; //"127.0.0.1";
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            //20390059

            //MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();// [1]
            //String xml = "/Users/lekanomotayo/projects/phoenix/src/main/resources/packagers/POST_PACKAGER.xml";
            String xml = "/Users/lekanomotayo/projects/phoenix/src/main/resources/packagers/NIBSS_PACKAGER.xml";
            String template = EndpointStarter.readFile(xml);
            StringReader reader = new StringReader(template);
            MessageFactory<IsoMessage> messageFactory = ConfigParser.createFromReader(reader);
            messageFactory.setUseBinaryBitmap(true);
            //messageFactory.setUseBinaryMessages(true);
            //messageFactory.setCharacterEncoding(StandardCharsets.US_ASCII.name());
            messageFactory.setCharacterEncoding(StandardCharsets.UTF_8.name());

            Iso8583Client<IsoMessage> client = new Iso8583Client<>(socketAddress, messageFactory);// [2]
            client.addMessageListener(new IsoMessageListener<IsoMessage>() { // [3]
                @Override
                public boolean applies(IsoMessage isoMessage) {
                    return true;
                }

                @Override
                public boolean onMessage(ChannelHandlerContext ctx, IsoMessage isoMessage) {
                    if(IsoUtil.isRequest(isoMessage)){
                        System.out.println("Incoming request to Client Bank B: \n" +isoMessage.debugString());
                        final IsoMessage response = client.getIsoMessageFactory().createResponse(isoMessage);
                        response.setField(39, IsoType.ALPHA.value("00", 2));
                        //response.setField(60, IsoType.LLLVAR.value("XXX", 3));
                        System.out.println("Outgoing response to Server Bank A: \n" +response.debugString());
                        ctx.writeAndFlush(response);
                    }
                    else{
                        String respCode = String.valueOf(isoMessage.getField(IsoUtil.f39).getValue());
                        String type = String.format("%04X", isoMessage.getType());
                        System.out.println("Incoming response type: " + type + " code: \n" + respCode);
                        System.out.println("Incoming response to Client Bank B: \n" + isoMessage.debugString());
                    }
                    return false;
                }
            });
            //server.getConfiguration().replyOnError(true);// [4]
            client.init();// [5]
            client.connect();

            if (client.isConnected()) { // [7]
                int i = 0;
                while(i++ < 2) {
                    //IsoMessage message1 = TestSample.createIsoMessage(messageFactory, stan++);
                    IsoMessage termParam = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9C");
                    IsoMessage callHome = TestSample.createNIBSSEchoIsoMessage(messageFactory, stan++);
                    IsoMessage masterKey = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9A");
                    IsoMessage sessionKey = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9B");
                    IsoMessage pinKey = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9G");
                    IsoMessage ipekTrack2 = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9I");
                    IsoMessage ipekEMV = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9J");

                    // Master Key, Session Key, Pin Key, Ipek Track 2, Ipek EMV download
                    IsoMessage ca = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9E");
                    IsoMessage aid = TestSample.createNIBSSDownloadKeyIsoMessage(messageFactory, stan++, "9F");

                    //System.out.println("Abt to send request: \n" + message.debugString());
                    client.send(termParam);// [8]
                    client.send(callHome);// [8]
                    client.send(masterKey);// [8]
                    client.send(sessionKey);// [8]
                    client.send(pinKey);// [8]
                    client.send(ipekTrack2);// [8]
                    client.send(ipekEMV);// [8]
                    //System.out.println("Message sent: \n" + message.debugString());
                    //client.sendAsync(message);// [8]
                }
            }

            Thread.sleep(60000);
            client.shutdown();// [8]
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
