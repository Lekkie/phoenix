package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Entity
@DynamicInsert
@Table(name = "tbl_route_by_cards")
@SuppressWarnings("serial")
public class RouteByCard extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "routing_group_id", nullable = false)
    private Long routingGroupId;
    @Column(name = "totals_group_id", nullable = false)
    private Long totalsGroupId;
    @Column(name = "transaction_group_id", nullable = false)
    private Long transactionGroupId;
    @Column(name = "sink_node_id", nullable = false)
    private Long sinkNodeId;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "status", nullable = false)
    private int status = 1;

    public RouteByCard(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalsGroupId() {
        return totalsGroupId;
    }

    public void setTotalsGroupId(Long totalsGroupId) {
        this.totalsGroupId = totalsGroupId;
    }

    public Long getRoutingGroupId() {
        return routingGroupId;
    }

    public void setRoutingGroupId(Long routingGroupId) {
        this.routingGroupId = routingGroupId;
    }

    public Long getTransactionGroupId() {
        return transactionGroupId;
    }

    public void setTransactionGroupId(Long transactionGroupId) {
        this.transactionGroupId = transactionGroupId;
    }

    public Long getSinkNodeId() {
        return sinkNodeId;
    }

    public void setSinkNodeId(Long sinkNodeId) {
        this.sinkNodeId = sinkNodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
