package com.avantir.phoenix.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Embeddable
public class SAPEndpointId implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(name = "src_node_id", nullable = true)
    private Long srcNodeId = 0L;
    @Column(name = "snk_node_id", nullable = true)
    private Long snkNodeId = 0L;

    public SAPEndpointId()
    {

    }

    public SAPEndpointId(Long srcNodeId, Long snkNodeId)
    {
        this.srcNodeId = srcNodeId;
        this.snkNodeId = snkNodeId;
    }

    public Long getSrcNodeId() {
        return srcNodeId;
    }

    public void setSrcNodeId(Long srcNodeId) {
        this.srcNodeId = srcNodeId;
    }

    public Long getSnkNodeId() {
        return snkNodeId;
    }

    public void setSnkNodeId(Long snkNodeId) {
        this.snkNodeId = snkNodeId;
    }
}
