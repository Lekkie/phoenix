package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Entity
@DynamicInsert
@Table(name = "tbl_card_products")
@SuppressWarnings("serial")
public class CardProduct extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "card_set_id", nullable = false)
    private Long cardSetId;
    @Column(name = "totals_group_id", nullable = false, unique = true)
    private Long totalsGroupId;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "status", nullable = false)
    private int status = 1;


    public CardProduct(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(Long cardSetId) {
        this.cardSetId = cardSetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalsGroupId() {
        return totalsGroupId;
    }

    public void setTotalsGroupId(Long totalsGroupId) {
        this.totalsGroupId = totalsGroupId;
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
