package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.AccountType;
import com.avantir.phoenix.model.SwitchKeyOrigDataElem;
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
public interface SwitchKeyOrigDataElemRepository extends JpaRepository<SwitchKeyOrigDataElem, String> {

    //@Cacheable(value = "endpointById")
    SwitchKeyOrigDataElem findById(@Param("id") Long id);
    //@Cacheable(value = "endpointByName")
    SwitchKeyOrigDataElem findByOriginalDataElementAllIgnoringCase(@Param("originalDataElement") String originalDataElement);

}
