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
public class ClientBankF {





    public static void main(String[] args){

        try{
            int port = 50002;
            String host = "127.0.0.1";
            SocketAddress socketAddress = new InetSocketAddress(host, port);

            //MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();// [1]
            String xml = "/Users/lekanomotayo/projects/phoenix/src/main/resources/packagers/POST_PACKAGER.xml";
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
                        System.out.println("Incoming request to Client Bank F: " +isoMessage.debugString());
                        final IsoMessage response = client.getIsoMessageFactory().createResponse(isoMessage);
                        response.setField(39, IsoType.ALPHA.value("00", 2));
                        //response.setField(60, IsoType.LLLVAR.value("XXX", 3));
                        ctx.writeAndFlush(response);
                    }
                    else{
                        System.out.println("Incoming response to Client Bank F: " + isoMessage.debugString());
                    }
                    return false;
                }
            });
            //server.getConfiguration().replyOnError(true);// [4]
            client.init();// [5]
            client.connect();

            if (client.isConnected()) { // [7]
                while(true) {

                    int type = 0x200;
                    String pan = "6280512006989001599";
                    String procCode = "000000";
                    String amt = "000000500000";
                    String transDatetime = "0612210530";
                    String stan = "220591";
                    String timelocal = "132130";// 13:21 (1:21 PM)
                    String datelocal = "0103"; //Jan 3rd.
                    String exp = "1911"; // 2019, Nov.
                    String merchType = "3401";// f18
                    String posEntryMode = "222"; //f22
                    String posCondCode = "10"; //f25
                    String posPinCaptureCode = "12"; //f26
                    String acqInstId = "62805198102";//f32
                    String tid = "30330003";
                    String mid = "AUTOPAY00000004";
                    String loc = "042458106804, 3IPG0001              LANG";
                    String curr = "566"; //f49
                    String recvInstId = "628051010";
                    String posDataCode = "100520609000090";

                    String keyHex = "43E6CB9D0127525C85B9697A076FF3B4"; // FC70F43030303030 -ChkDigit
                    byte[] keyBytes = keyHex.getBytes();
                    byte[] keyHexBytes = HexConverter.fromHex2ByteArray(keyBytes);
                    //byte key[] = new byte[]{68, -28, -110, -79, 28, 58, 92, -21, -25, 63, -75, -114, 113, -105, -72, -109, -30, -68, 25, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48};
                    //String hex = HexConverter.fromBinary2Hex(key);
                    String field127_2 = "1200493049";
                    String field127_22 = "211AutoPayInfo3450&lt;AutoPayInfo&gt;&lt;BatchId&gt;1980&lt;/BatchId&gt;&lt;BatchDescription&gt;bulk&lt;/BatchDescription&gt;&lt;BeneficiaryName&gt;PREPAID&lt;/BeneficiaryName&gt;&lt;RecvBank&gt;011&lt;/RecvBank&gt;&lt;InitBank&gt;058&lt;/InitBank&gt;&lt;Sponsor&gt;ISW&lt;/Sponsor&gt;&lt;CorporateCode&gt;SC1&lt;/CorporateCode&gt;&lt;Surcharge&gt;4000&lt;/Surcharge&gt;&lt;TranRef&gt;635533051281190000&lt;/TranRef&gt;&lt;Narration&gt;63553305/PREPAID SELENIUM/1980/&lt;/Narration&gt;&lt;PaymentRef&gt;bulk/SC1/058/QzpP318/&lt;/PaymentRef&gt;&lt;CorporateName&gt;Selenium Corporate&lt;/CorporateName&gt;&lt;/AutoPayInfo&gt;";
                    String field127_23 = "5060990580000157166                Oko Awo                            Lagos                              Nigeria                            Lagos                              Lagos               234                 NGR5060990580000157166                ";


                    IsoMessage message = messageFactory.newMessage(type);
                    IsoMessage templ = messageFactory.getMessageTemplate(type);
                    message.setValue(f2, pan, templ.getField(f2).getType(), pan.length()); // LLVAR
                    message.setValue(f3, procCode, templ.getField(f3).getType(), templ.getField(f3).getLength()); // LLVAR
                    message.setValue(f4, amt, templ.getField(f4).getType(), templ.getField(f4).getLength()); // LLVAR
                    message.setValue(f7, transDatetime, templ.getField(f7).getType(), templ.getField(f7).getLength());
                    message.setValue(f11, stan, templ.getField(f11).getType(), templ.getField(f11).getLength());
                    message.setValue(f12, timelocal, templ.getField(f12).getType(), templ.getField(f12).getLength());
                    message.setValue(f13, datelocal, templ.getField(f13).getType(), templ.getField(f13).getLength());
                    message.setValue(f14, exp, templ.getField(f14).getType(), templ.getField(f14).getLength());
                    message.setValue(f18, merchType, templ.getField(f18).getType(), templ.getField(f18).getLength());
                    message.setValue(f22, posEntryMode, templ.getField(f22).getType(), templ.getField(f22).getLength());
                    message.setValue(f25, posCondCode, templ.getField(f25).getType(), templ.getField(f25).getLength());
                    message.setValue(f26, posPinCaptureCode, templ.getField(f26).getType(), templ.getField(f26).getLength());
                    message.setValue(f32, acqInstId, templ.getField(f32).getType(), templ.getField(f32).getLength());
                    message.setValue(f41, tid, templ.getField(f41).getType(), templ.getField(f41).getLength());
                    message.setValue(f42, mid, templ.getField(f42).getType(), templ.getField(f42).getLength());
                    message.setValue(f43, loc, templ.getField(f43).getType(), templ.getField(f43).getLength());
                    message.setValue(f49, curr, templ.getField(f49).getType(), templ.getField(f49).getLength());
                    //message.setValue(f52, keyHexBytes, templ.getField(f52).getType(), templ.getField(f52).getLength());
                    message.setValue(f100, recvInstId, templ.getField(f100).getType(), templ.getField(f100).getLength());
                    message.setValue(f123, posDataCode, templ.getField(f123).getType(), templ.getField(f123).getLength());

                    CompositeField compositeField = new CompositeField();
                    IsoValue isoValue127 = templ.getField(f127);
                    CompositeField compositeField127 = (CompositeField) isoValue127.getValue();
                    compositeField
                            .addValue(f2, new IsoValue<String>(compositeField127.getField(f2).getType(), field127_2, field127_2.length())) // LLVAR
                            .addValue(f22, new IsoValue<String>(compositeField127.getField(f22).getType(), field127_22, field127_22.length())) // LLLLLVAR
                            .addValue(f23, new IsoValue<String>(compositeField127.getField(f23).getType(), field127_23, compositeField127.getField(f23).getLength()));
                    message.setValue(f127, compositeField, compositeField, isoValue127.getType(), (field127_22.length() + field127_23.length()));

                    client.send(message);// [8]
                    //client.sendAsync(message);// [8]

                    System.out.println("Test Client F");
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
