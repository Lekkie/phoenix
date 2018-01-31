package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.RoutingGroup;
import com.avantir.phoenix.model.TotalsGroup;
import com.avantir.phoenix.repository.RoutingGroupRepository;
import com.avantir.phoenix.repository.TotalsGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class RoutingGroupService {

    @Autowired
    private RoutingGroupRepository routingGroupRepository;


    @Transactional(readOnly=true)
    public RoutingGroup findByRoutingGroupId(Long nodeId) {

        try
        {
            return routingGroupRepository.findById(nodeId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly=true)
    public RoutingGroup findByRoutingGroupName(String name) {

        try
        {
            return routingGroupRepository.findByNameAllIgnoringCase(name);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
