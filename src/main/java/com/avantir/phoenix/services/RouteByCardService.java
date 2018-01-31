package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.RouteByCard;
import com.avantir.phoenix.model.RouteByReceivingInst;
import com.avantir.phoenix.repository.RouteByCardRepository;
import com.avantir.phoenix.repository.RouteByReceivingInstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class RouteByCardService {

    @Autowired
    private RouteByCardRepository routeByCardRepository;


    @Transactional(readOnly=true)
    public RouteByCard findByRoutingGroupIdTotalsGroupId(Long routingGroupId, Long totalsGroupId) {

        try
        {
            return routeByCardRepository.findByRoutingGroupIdTotalsGroupIdTransactionGroupId(routingGroupId, totalsGroupId, 0L);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
