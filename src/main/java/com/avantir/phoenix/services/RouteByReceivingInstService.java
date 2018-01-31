package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.RouteByReceivingInst;
import com.avantir.phoenix.model.RoutingGroup;
import com.avantir.phoenix.repository.RouteByReceivingInstRepository;
import com.avantir.phoenix.repository.RoutingGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class RouteByReceivingInstService {

    @Autowired
    private RouteByReceivingInstRepository routeByReceivingInstRepository;


    @Transactional(readOnly=true)
    public RouteByReceivingInst findByReceivingInst(Long routingGroupId, String recevingInst) {

        try
        {
            return routeByReceivingInstRepository.findByRoutingGroupIdReceivingInstId(routingGroupId, recevingInst);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
