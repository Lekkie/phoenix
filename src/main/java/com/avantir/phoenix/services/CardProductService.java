package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.CardProduct;
import com.avantir.phoenix.model.CardSetBin;
import com.avantir.phoenix.repository.CardProductRepository;
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
public class CardProductService {

    @Autowired
    private CardProductRepository cardProductRepository;


    @Transactional(readOnly=true)
    public CardProduct findByCardSetId(Long cardSetId) {

        try
        {
            return cardProductRepository.findByCardSetId(cardSetId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
