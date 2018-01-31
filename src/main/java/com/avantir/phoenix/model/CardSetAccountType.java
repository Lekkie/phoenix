package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Entity
@DynamicInsert
@Table(name = "tbl_card_set_account_types")
@SuppressWarnings("serial")
public class CardSetAccountType extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_set_id", nullable = false)
    private Long cardSetId;
    @Column(name = "account_type_id", nullable = false, unique = true)
    private Long accountTypeId;
    */
    //Make composite key
    @EmbeddedId
    CardSetAccountTypeId cardSetAccountTypeId;


    public CardSetAccountType(){

    }

    public CardSetAccountTypeId getCardSetAccountTypeId() {
        return cardSetAccountTypeId;
    }

    public void setCardSetAccountTypeId(CardSetAccountTypeId cardSetAccountTypeId) {
        this.cardSetAccountTypeId = cardSetAccountTypeId;
    }
}
