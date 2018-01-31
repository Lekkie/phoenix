package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.RouteByCard;
import com.avantir.phoenix.model.RoutingGroup;
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
public interface RouteByCardRepository extends JpaRepository<RouteByCard, String> {

    //@Cacheable(value = "endpointById")
    RouteByCard findById(@Param("id") Long id);
    //@Cacheable(value = "endpointByName")
    @Query("FROM RouteByCard r WHERE r.routingGroupId = :routingGroupId AND r.totalsGroupId = :totalsGroupId AND (r.transactionGroupId) = :transactionGroupId")
    RouteByCard findByRoutingGroupIdTotalsGroupIdTransactionGroupId(@Param("routingGroupId") Long routingGroupId, @Param("totalsGroupId") Long totalsGroupId, @Param("transactionGroupId") Long transactionGroupId);


}
