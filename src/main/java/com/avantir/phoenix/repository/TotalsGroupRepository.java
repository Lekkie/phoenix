package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.TotalsGroup;
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
public interface TotalsGroupRepository extends JpaRepository<TotalsGroup, String> {

    //@Cacheable(value = "endpointById")
    TotalsGroup findById(@Param("id") Long id);


}
