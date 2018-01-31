package com.avantir.phoenix.messaging;

import com.avantir.phoenix.model.ISOBridge;
import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.SAPEndpoint;
import com.avantir.phoenix.model.TCPEndpoint;
import com.avantir.phoenix.services.ISOBridgeService;
import com.avantir.phoenix.services.NodeService;
import com.avantir.phoenix.services.SAPEndpointService;
import com.avantir.phoenix.services.TCPEndpointService;
import com.avantir.phoenix.tranmgr.TranMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Component
public class EndpointStarter {


    //@Autowired
    //static ;

    public EndpointStarter(){

    }

    @Autowired
    public EndpointStarter(TranMgr tranMgr, TCPEndpointService tcpEndpointService, SAPEndpointService sapEndpointService, NodeService nodeService, ISOBridgeService isoBridgeService){
        List<TCPEndpoint> TCPEndpointList = tcpEndpointService.findAllActive();
        if(TCPEndpointList != null && TCPEndpointList.size() > 0){
            for(TCPEndpoint tcpEndpoint : TCPEndpointList){
                SAPEndpoint sapEndpoint = sapEndpointService.findByTcpEndpointId(tcpEndpoint.getId());
                ISOBridge isoBridge = isoBridgeService.findByISOBridgeId(sapEndpoint.getIsoBridgeId());
                try{
                    start(tranMgr, sapEndpoint, nodeService, tcpEndpoint, isoBridge);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }


    public static void start(TranMgr tranMgr, SAPEndpoint sapEndpoint, NodeService nodeService, TCPEndpoint tcpEndpoint, ISOBridge isoBridge)throws Exception{
        SAPInterchange sapInterchange = new SAPInterchange(tranMgr, sapEndpoint, nodeService, tcpEndpoint, isoBridge);
        sapInterchange.start();
        TranMgr.getSapInterchanges().put(sapEndpoint.getId(), sapInterchange);
    }

    public static void stop(SAPEndpoint sapEndpoint){
        SAPInterchange sapInterchange = TranMgr.getSapInterchanges().get(sapEndpoint.getId());
        stopSAPInterchange(sapInterchange);
    }

    @PreDestroy
    public void stop() {
        for(Map.Entry<Long, SAPInterchange> entry : TranMgr.getSapInterchanges().entrySet()) {
            Long key = entry.getKey();
            SAPInterchange value = entry.getValue();
            stopSAPInterchange(value);
        }
    }

    private static void stopSAPInterchange(SAPInterchange sapInterchange){
        sapInterchange.stop();
    }



    public static void main(String[] args){
        try{
            //ISOPackager isoPackager = new ISOPackager();
            String template = EndpointStarter.readFile("/Users/lekanomotayo/projects/phoenix/j8583.xml");
            //isoPackager.setId(1L);
            //isoPackager.setName("PostPackager");
            //isoPackager.setTemplate(template);

            SAPEndpoint sapEndpoint1 = new SAPEndpoint();
            sapEndpoint1.setName("TestInterchange1");
            sapEndpoint1.setSnkNodeId(1L);
            sapEndpoint1.setSrcNodeId(2L);
            sapEndpoint1.setTcpEndpointId(1L);

            TCPEndpoint tcpEndpoint1 = new TCPEndpoint();
            tcpEndpoint1.setName("IP Address");
            tcpEndpoint1.setDescription("");
            tcpEndpoint1.setId(1L);
            tcpEndpoint1.setServer(true);
            tcpEndpoint1.setStatus(1);
            //TCPEndpointPortIP tcpEndpointPortIP1 = new TCPEndpointPortIP("0.0.0.0", 9000);
            //tcpEndpoint1.setTcpEndpointPortIP(tcpEndpointPortIP1);
            tcpEndpoint1.setIp("0.0.0.0");
            tcpEndpoint1.setPort(9000);

            SAPEndpoint sapEndpoint2 = new SAPEndpoint();
            sapEndpoint2.setName("TestInterchange2");
            sapEndpoint2.setSnkNodeId(1L);
            sapEndpoint2.setSrcNodeId(2L);
            sapEndpoint2.setTcpEndpointId(1L);

            TCPEndpoint tcpEndpoint2 = new TCPEndpoint();
            tcpEndpoint2.setName("IP Address");
            tcpEndpoint2.setDescription("");
            tcpEndpoint2.setId(1L);
            tcpEndpoint2.setServer(false);
            tcpEndpoint2.setStatus(1);
            //TCPEndpointPortIP tcpEndpointPortIP2 = new TCPEndpointPortIP("127.0.0.1", 9000);
            //tcpEndpoint2.setTcpEndpointPortIP(tcpEndpointPortIP2);
            tcpEndpoint1.setIp("127.0.0.1");
            tcpEndpoint1.setPort(9000);

            EndpointStarter endpointStarter = new EndpointStarter();
            //endpointStarter.start(sapEndpoint1, tcpEndpoint1, isoPackager);
            //endpointStarter.start(sapEndpoint2, tcpEndpoint2, isoPackager);

            System.out.println("Test:");

            /*
            MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();// [1]
            SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9000);
            Iso8583Client<IsoMessage> client = new Iso8583Client<>(socketAddress, messageFactory);// [2]
            client.addMessageListener(new IsoMessageListener<IsoMessage>() { // [3]

                @Override
                public boolean applies(IsoMessage isoMessage) {
                    return isoMessage.getType() ==  0x800;
                }

                @Override
                public boolean onMessage(ChannelHandlerContext ctx, IsoMessage isoMessage) {
                    final IsoMessage response = client.getIsoMessageFactory().createResponse(isoMessage);
                    response.setField(39, IsoType.ALPHA.value("00", 2));
                    response.setField(60, IsoType.LLLVAR.value("XXX", 3));
                    ctx.writeAndFlush(response);
                    return false;
                }

            });
            //client.getConfiguration().replyOnError(true);// [4]
            client.init();// [5]
            client.connectAsync();// [6]
            if (client.isConnected()) { // [7]

                IsoMessage message = messageFactory.newMessage(0x800);
                client.send(message);// [8]
            }
            client.shutdown();// [9]
            */

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public static String readFile(String filename)
    {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }catch(Exception ex){}
        }
        return content;
    }


}
