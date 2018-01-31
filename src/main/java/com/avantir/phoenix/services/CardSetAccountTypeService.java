package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.CardSetAccountType;
import com.avantir.phoenix.model.CardSetAccountTypeId;
import com.avantir.phoenix.model.CardSetBin;
import com.avantir.phoenix.repository.CardSetAccountTypeRepository;
import com.avantir.phoenix.repository.CardSetBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class CardSetAccountTypeService {

    @Autowired
    private CardSetAccountTypeRepository cardSetAccountTypeRepository;


    @Transactional(readOnly=true)
    public CardSetAccountType findByCardSetIdAccountTypeId(Long cardSetId, Long accountTypeId) {

        try
        {
            CardSetAccountTypeId cardSetAccountTypeId = new CardSetAccountTypeId(cardSetId, accountTypeId);
            return cardSetAccountTypeRepository.findByCardSetAccountTypeIdAllIgnoringCase(cardSetAccountTypeId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
