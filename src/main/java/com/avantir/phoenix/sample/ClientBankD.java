package com.avantir.phoenix.sample;

import com.avantir.phoenix.messaging.EndpointStarter;
import com.avantir.phoenix.messaging.ISO8583Node;
import com.avantir.phoenix.utils.IsoUtil;
import com.github.kpavlov.jreactive8583.IsoMessageListener;
import com.github.kpavlov.jreactive8583.client.Iso8583Client;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.codecs.CompositeField;
import com.solab.iso8583.parse.ConfigParser;
import com.solab.iso8583.util.HexConverter;
import io.netty.channel.ChannelHandlerContext;

import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Created by lekanomotayo on 02/01/2018.
 */
public class ClientBankD {

    public static int stan = 1;

    public static void main(String[] args){

        try{
            int port = 60000;
            String host = "192.168.1.66"; //"127.0.0.1";
            SocketAddress socketAddress = new InetSocketAddress(host, port);

            /*
            //MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();// [1]
            String xml = "/Users/lekanomotayo/projects/phoenix/src/main/resources/packagers/POST_PACKAGER.xml";
            String template = EndpointStarter.readFile(xml);
            StringReader reader = new StringReader(template);
            MessageFactory<IsoMessage> messageFactory = ConfigParser.createFromReader(reader);
            messageFactory.setUseBinaryBitmap(true);
            //messageFactory.setUseBinaryMessages(true);
            //messageFactory.setCharacterEncoding(StandardCharsets.US_ASCII.name());
            messageFactory.setCharacterEncoding(StandardCharsets.UTF_8.name());
            */

            MessageFactory<IsoMessage> messageFactory = IsoUtil.getMessageFactory("POST_PACKAGER.xml", true);

            Iso8583Client<IsoMessage> client = new Iso8583Client<>(socketAddress, messageFactory);// [2]
            client.addMessageListener(new IsoMessageListener<IsoMessage>() { // [3]
                @Override
                public boolean applies(IsoMessage isoMessage) {
                    return true;
                }

                @Override
                public boolean onMessage(ChannelHandlerContext ctx, IsoMessage isoMessage) {
                    if(IsoUtil.isRequest(isoMessage)){
                        System.out.println("Incoming request to Client Bank D: " +isoMessage.debugString());
                        final IsoMessage response = client.getIsoMessageFactory().createResponse(isoMessage);
                        response.setField(39, IsoType.ALPHA.value("00", 2));
                        //response.setField(60, IsoType.LLLVAR.value("XXX", 3));
                        ctx.writeAndFlush(response);
                    }
                    else{
                        System.out.println("Incoming response to Client Bank D: " + isoMessage.debugString());
                    }
                    return false;
                }
            });
            //server.getConfiguration().replyOnError(true);// [4]
            client.init();// [5]
            client.connect();

            if (client.isConnected()) { // [7]
                int i = 0;
                while(i++ < 5) {
                    IsoMessage message = TestSample.createIsoMessage(messageFactory, stan++);
                    System.out.println("Abt to send request: \n" + message.debugString());
                    client.send(message);// [8]
                    System.out.println("Message sent: \n" + message.debugString());
                    //client.sendAsync(message);// [8]
                }
            }

            client.shutdown();// [8]
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    final static int f2 = 2;
    final static int f3 = 3;
    final static int f4 = 4;
    final static int f5 = 5;
    final static int f6 = 6;
    final static int f7 = 7;
    final static int f8 = 8;
    final static int f9 = 9;
    final static int f10 = 10;
    final static int f11 = 11;
    final static int f12 = 12;
    final static int f13 = 13;
    final static int f14 = 14;
    final static int f15 = 15;
    final static int f16 = 16;
    final static int f17 = 17;
    final static int f18 = 18;
    final static int f19 = 19;
    final static int f20 = 20;
    final static int f21 = 21;
    final static int f22 = 22;
    final static int f23 = 23;
    final static int f24 = 24;
    final static int f25 = 25;
    final static int f26 = 26;
    final static int f27 = 27;
    final static int f28 = 28;
    final static int f29 = 29;
    final static int f30 = 30;
    final static int f31 = 31;
    final static int f32 = 32;
    final static int f33 = 33;
    final static int f34 = 34;
    final static int f35 = 35;
    final static int f36 = 36;
    final static int f37 = 37;
    final static int f38 = 38;
    final static int f39 = 39;
    final static int f40 = 10;
    final static int f41 = 41;
    final static int f42 = 42;
    final static int f43 = 43;
    final static int f44 = 44;
    final static int f45 = 45;
    final static int f46 = 46;
    final static int f47 = 47;
    final static int f48 = 48;
    final static int f49 = 49;
    final static int f50 = 50;
    final static int f51 = 51;
    final static int f52 = 52;
    final static int f53 = 53;
    final static int f54 = 54;
    final static int f55 = 55;
    final static int f56 = 56;
    final static int f57 = 57;
    final static int f58 = 58;
    final static int f59 = 59;
    final static int f60 = 60;
    final static int f61 = 61;
    final static int f62 = 62;
    final static int f63 = 63;
    final static int f64 = 64;
    final static int f65 = 65;
    final static int f66 = 66;
    final static int f67 = 67;
    final static int f68 = 68;
    final static int f69 = 69;
    final static int f70 = 70;
    final static int f71 = 71;
    final static int f72 = 72;
    final static int f73 = 73;
    final static int f74 = 74;
    final static int f75 = 75;
    final static int f76 = 76;
    final static int f77 = 77;
    final static int f78 = 78;
    final static int f79 = 79;
    final static int f80 = 80;
    final static int f81 = 81;
    final static int f82 = 82;
    final static int f83 = 83;
    final static int f84 = 84;
    final static int f85 = 85;
    final static int f86 = 86;
    final static int f87 = 87;
    final static int f88 = 88;
    final static int f89 = 89;
    final static int f90 = 90;
    final static int f91 = 91;
    final static int f92 = 92;
    final static int f93 = 93;
    final static int f94 = 94;
    final static int f95 = 95;
    final static int f96 = 96;
    final static int f97 = 97;
    final static int f98 = 98;
    final static int f99 = 99;
    final static int f100 = 100;
    final static int f101 = 101;
    final static int f102 = 102;
    final static int f103 = 103;
    final static int f104 = 104;
    final static int f105 = 105;
    final static int f106 = 106;
    final static int f107 = 107;
    final static int f108 = 108;
    final static int f109 = 109;
    final static int f110 = 110;
    final static int f111 = 111;
    final static int f112 = 112;
    final static int f113 = 113;
    final static int f114 = 114;
    final static int f115 = 115;
    final static int f116 = 116;
    final static int f117 = 117;
    final static int f118 = 118;
    final static int f119 = 119;
    final static int f120 = 120;
    final static int f121 = 121;
    final static int f122 = 122;
    final static int f123 = 123;
    final static int f124 = 124;
    final static int f125 = 125;
    final static int f126 = 126;
    final static int f127 = 127;


}
