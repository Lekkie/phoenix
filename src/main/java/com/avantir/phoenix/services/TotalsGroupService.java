package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.TotalsGroup;
import com.avantir.phoenix.repository.NodeRepository;
import com.avantir.phoenix.repository.TotalsGroupRepository;
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
public class TotalsGroupService {

    @Autowired
    private TotalsGroupRepository totalsGroupRepository;


    @Transactional(readOnly=true)
    public TotalsGroup findById(Long id) {

        try
        {
            return totalsGroupRepository.findById(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
