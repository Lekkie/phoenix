package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.RouteByCard;
import com.avantir.phoenix.model.RouteByReceivingInst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Repository
@Transactional
public interface RouteByReceivingInstRepository extends JpaRepository<RouteByReceivingInst, String> {

    //@Cacheable(value = "endpointById")
    RouteByReceivingInst findById(@Param("id") Long id);
    //@Cacheable(value = "endpointByName")
    @Query("FROM RouteByReceivingInst r WHERE r.routingGroupId = :routingGroupId AND r.receivingInstId = :receivingInstId")
    RouteByReceivingInst findByRoutingGroupIdReceivingInstId(@Param("routingGroupId") Long routingGroupId, @Param("receivingInstId") String receivingInstId);

}
