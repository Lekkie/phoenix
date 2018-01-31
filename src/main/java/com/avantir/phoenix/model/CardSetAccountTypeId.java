package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Embeddable
public class CardSetAccountTypeId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "card_set_id", nullable = false)
    private Long cardSetId;
    @Column(name = "account_type_id", nullable = false)
    private Long accountTypeId;


    public CardSetAccountTypeId(){
    }


    public CardSetAccountTypeId(Long cardSetId, Long accountTypeId){
        this.cardSetId = cardSetId;
        this.accountTypeId = accountTypeId;
    }


    public Long getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(Long cardSetId) {
        this.cardSetId = cardSetId;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }
}
