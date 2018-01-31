package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.CardAcceptor;
import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.repository.CardAcceptorRepository;
import com.avantir.phoenix.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class CardAcceptorService {

    @Autowired
    private CardAcceptorRepository cardAcceptorRepository;


    @Transactional(readOnly=true)
    public CardAcceptor findByCardAcceptorId(Long nodeId) {

        try
        {
            return cardAcceptorRepository.findById(nodeId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Transactional(readOnly=true)
    public CardAcceptor findByCode(String cardAcceptorCode) {

        try
        {
            return cardAcceptorRepository.findByCodeAllIgnoringCase(cardAcceptorCode);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
