package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Repository
@Transactional
public interface SAPEndpointRepository extends JpaRepository<SAPEndpoint, String> {

    //@Cacheable(value = "endpointById")
    SAPEndpoint findById(@Param("id") Long id);
    //@Cacheable(value = "routeByRouteId")
    SAPEndpoint findByNameAllIgnoringCase(String name);
    //@Cacheable(value = "endpointById")
    SAPEndpoint findByTcpEndpointId(@Param("tcpEndpointId") Long tcpEndpointId);


}
