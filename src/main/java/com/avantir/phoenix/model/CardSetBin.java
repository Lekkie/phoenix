package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Entity
@DynamicInsert
@Table(name = "tbl_card_set_bins")
@SuppressWarnings("serial")
public class CardSetBin extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_set_id", nullable = false)
    private Long cardSetId;
    @Column(name = "bin", nullable = false, unique = true)
    private String bin;


    public CardSetBin(){

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

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }
}
