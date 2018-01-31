package com.avantir.phoenix.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Embeddable
public class TCPEndpointPortIP implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "ip", nullable = true)
    private String ip;
    @Column(name = "port", nullable = false)
    private int port = 0;

    public TCPEndpointPortIP(){

    }

    public TCPEndpointPortIP(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
