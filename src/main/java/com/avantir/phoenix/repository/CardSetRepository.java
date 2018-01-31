package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.CardSet;
import com.avantir.phoenix.model.RoutingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Repository
@Transactional
public interface CardSetRepository extends JpaRepository<CardSet, String> {

    //@Cacheable(value = "endpointById")
    CardSet findById(@Param("id") Long id);


}
