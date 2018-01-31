package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.CardSetBin;
import com.avantir.phoenix.model.TotalsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Repository
@Transactional
public interface CardSetBinRepository extends JpaRepository<CardSetBin, String> {

    //@Cacheable(value = "endpointById")
    CardSetBin findByBin(@Param("bin") String bin);


}
