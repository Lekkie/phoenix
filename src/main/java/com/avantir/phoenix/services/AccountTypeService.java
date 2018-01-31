package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.AccountType;
import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.repository.AccountTypeRepository;
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
public class AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;


    @Transactional(readOnly=true)
    public AccountType findByAccountTypeId(Long id) {

        try
        {
            return accountTypeRepository.findById(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly=true)
    public AccountType findByAccountTypeCode(String code) {

        try
        {
            return accountTypeRepository.findByCodeAllIgnoringCase(code);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Transactional(readOnly=true)
    public List<AccountType> findAllActive() {

        try
        {
            List<AccountType> list = accountTypeRepository.findByStatus(1);
            return list;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
