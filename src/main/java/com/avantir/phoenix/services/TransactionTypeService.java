package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.AccountType;
import com.avantir.phoenix.model.TransactionType;
import com.avantir.phoenix.repository.AccountTypeRepository;
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
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;


    @Transactional(readOnly=true)
    public TransactionType findById(Long id) {

        try
        {
            return transactionTypeRepository.findById(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly=true)
    public TransactionType findByCode(String code) {

        try
        {
            return transactionTypeRepository.findByCodeAllIgnoringCase(code);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Transactional(readOnly=true)
    public List<TransactionType> findAllActive() {

        try
        {
            List<TransactionType> list = transactionTypeRepository.findByStatus(1);
            return list;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
