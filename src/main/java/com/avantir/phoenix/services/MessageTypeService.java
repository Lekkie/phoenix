package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.MessageType;
import com.avantir.phoenix.model.TransactionType;
import com.avantir.phoenix.repository.MessageTypeRepository;
import com.avantir.phoenix.repository.TransactionTypeRepository;
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
public class MessageTypeService {

    @Autowired
    private MessageTypeRepository messageTypeRepository;


    @Transactional(readOnly=true)
    public MessageType findById(Long id) {

        try
        {
            return messageTypeRepository.findById(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly=true)
    public MessageType findByCode(String code) {

        try
        {
            return messageTypeRepository.findByCodeAllIgnoringCase(code);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Transactional(readOnly=true)
    public List<MessageType> findAllActive() {

        try
        {
            List<MessageType> list = messageTypeRepository.findByStatus(1);
            return list;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
