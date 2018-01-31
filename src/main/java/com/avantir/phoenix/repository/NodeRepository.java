package com.avantir.phoenix.repository;

import com.avantir.phoenix.model.Node;
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
public interface NodeRepository extends JpaRepository<Node, String> {

    //@Cacheable(value = "endpointById")
    Node findById(@Param("id") Long id);
    //@Cacheable(value = "endpointByName")
    Node findByNameAllIgnoringCase(@Param("name") String name);
    @Query("FROM Node n WHERE n.status = :status")
    List<Node> findByStatus(@Param("status") int status);


}
