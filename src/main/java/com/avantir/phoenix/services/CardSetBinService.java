package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.AccountType;
import com.avantir.phoenix.model.CardSetBin;
import com.avantir.phoenix.repository.AccountTypeRepository;
import com.avantir.phoenix.repository.CardSetBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class CardSetBinService {

    @Autowired
    private CardSetBinRepository cardSetBinRepository;


    @Transactional(readOnly=true)
    public CardSetBin findByBin(String bin) {

        try
        {
            return cardSetBinRepository.findByBin(bin);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly=true)
    public CardSetBin findByPan(String pan) {

        try
        {
            List<CardSetBin> cardSetBinList = cardSetBinRepository.findAll();
            List<CardSetBin> matchedCardSetBinList = new ArrayList<CardSetBin>();
            for(CardSetBin cardSetBin: cardSetBinList){
                String bin = cardSetBin.getBin();
                if(pan.startsWith(bin))
                    matchedCardSetBinList.add(cardSetBin);
            }
            if(matchedCardSetBinList.size() > 1){
                CardSetBin maxLenCardSetBin = matchedCardSetBinList.get(0);
                int maxLenBin = maxLenCardSetBin.getBin().length();
                for(CardSetBin cardSetBin : matchedCardSetBinList){
                    if(maxLenBin < cardSetBin.getBin().length())
                        maxLenCardSetBin = cardSetBin;
                }
                return maxLenCardSetBin;
            }
            else if(matchedCardSetBinList.size() == 1){
                return matchedCardSetBinList.get(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
