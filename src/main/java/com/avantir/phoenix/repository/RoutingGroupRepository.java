package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.RoutingGroup;
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
public interface RoutingGroupRepository extends JpaRepository<RoutingGroup, String> {

    //@Cacheable(value = "endpointById")
    RoutingGroup findById(@Param("id") Long id);
    RoutingGroup findByNameAllIgnoringCase(@Param("name") String name);


}
