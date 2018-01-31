package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.ISOBridge;
import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;


    @Transactional(readOnly=true)
    public Node findByNodeId(Long nodeId) {

        try
        {
            return nodeRepository.findById(nodeId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Transactional(readOnly=true)
    public List<Node> findAllActive() {

        try
        {
            List<Node> endpointList = nodeRepository.findByStatus(1);
            return endpointList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
