package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.CardProduct;
import com.avantir.phoenix.model.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Repository
@Transactional
public interface CardProductRepository extends JpaRepository<CardProduct, String> {

    //@Cacheable(value = "endpointById")
    CardProduct findById(@Param("id") Long id);
    //@Cacheable(value = "endpointById")
    CardProduct findByCardSetId(@Param("cardSetId") Long cardSetId);

}
