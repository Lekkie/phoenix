package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.CardSetAccountType;
import com.avantir.phoenix.model.CardSetAccountTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Repository
@Transactional
public interface CardSetAccountTypeRepository extends JpaRepository<CardSetAccountType, CardSetAccountTypeId> {

    //@Cacheable(value = "endpointById")
    CardSetAccountType findByCardSetAccountTypeIdAllIgnoringCase(CardSetAccountTypeId cardSetAccountTypeId);


}
