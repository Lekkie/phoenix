package com.avantir.phoenix.messaging;

import com.avantir.phoenix.model.ISOBridge;
import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.SAPEndpoint;
import com.avantir.phoenix.model.TCPEndpoint;
import com.avantir.phoenix.services.NodeService;
import com.avantir.phoenix.tranmgr.TranMgr;

import java.util.TreeMap;

/**
 * Created by lekanomotayo on 04/01/2018.
 */
public class SAPInterchange {

    TranMgr tranMgr;
    SAPEndpoint sapEndpoint;
    ISO8583SrcNode iso8583SrcNode;
    ISO8583SinkNode iso8583SinkNode;
    NodeService nodeService;
    ISOBridge isoBridge;
    TCPEndpoint tcpEndpoint;
    IConnection connection;


    public SAPInterchange(TranMgr tranMgr, SAPEndpoint sapEndpoint, NodeService nodeService, TCPEndpoint tcpEndpoint, ISOBridge isoBridge){
        this.tranMgr = tranMgr;
        this.sapEndpoint = sapEndpoint;
        this.nodeService = nodeService;
        this.tcpEndpoint = tcpEndpoint;
        this.isoBridge = isoBridge;
    }

    public void start() throws Exception{
        if(sapEndpoint == null)
            throw new Exception("Cannot load SAP Endpoint config");

        if(tcpEndpoint == null)
            throw new Exception("Cannot load TCP Endpoint config");

        if(tcpEndpoint.isServer()){
            try{
                connection = new ServerConnection(tcpEndpoint, isoBridge);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            try{
                connection = new ClientConnection(tcpEndpoint, isoBridge);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }

        SrcNodeListener srcNodeListener = null;
        SinkNodeListener sinkNodeListener = null;
        Long srcNodeId = sapEndpoint.getSrcNodeId();
        Long snkNodeId = sapEndpoint.getSnkNodeId();
        if(srcNodeId > 0) {
            Node node = nodeService.findByNodeId(srcNodeId);
            iso8583SrcNode = new ISO8583SrcNode(tranMgr, sapEndpoint, node, isoBridge, connection);
            TranMgr.addSrcNode(srcNodeId, iso8583SrcNode);
            srcNodeListener = new SrcNodeListener(iso8583SrcNode);
            connection.setSrcNodeListener(srcNodeListener);
        }
        if(snkNodeId > 0) {
            Node node = nodeService.findByNodeId(snkNodeId);
            iso8583SinkNode = new ISO8583SinkNode(tranMgr, sapEndpoint, node, isoBridge, connection);
            TranMgr.addSinkNode(snkNodeId, iso8583SinkNode);
            sinkNodeListener = new SinkNodeListener(iso8583SinkNode);
            connection.setSinkNodeListener(sinkNodeListener);
        }
        connection.start();
    }


    public void stop(){
        if(connection instanceof ServerConnection){
            ServerConnection serverTCPEndpoint = (ServerConnection) connection;
            serverTCPEndpoint.stop();
        }
        else if(connection instanceof ClientConnection){
            ClientConnection clientConnection = (ClientConnection) connection;
            clientConnection.stop();
        }
    }


    public ISO8583SinkNode getIso8583SinkNode() {
        return iso8583SinkNode;
    }

    public ISO8583SrcNode getIso8583SrcNode() {
        return iso8583SrcNode;
    }
}
