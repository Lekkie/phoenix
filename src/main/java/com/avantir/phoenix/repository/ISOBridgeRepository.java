package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.ISOBridge;
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
public interface ISOBridgeRepository extends JpaRepository<ISOBridge, String> {

    //@Cacheable(value = "endpointById")
    ISOBridge findById(@Param("id") Long id);
    //@Cacheable(value = "endpointByName")
    ISOBridge findByNameAllIgnoringCase(@Param("name") String name);
    @Query("FROM Node n WHERE n.status = :status")
    List<ISOBridge> findByStatus(@Param("status") int status);


}
