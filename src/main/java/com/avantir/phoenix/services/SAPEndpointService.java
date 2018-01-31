package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.Node;
import com.avantir.phoenix.model.SAPEndpoint;
import com.avantir.phoenix.model.TCPEndpoint;
import com.avantir.phoenix.repository.SAPEndpointRepository;
import com.avantir.phoenix.repository.TCPEndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class SAPEndpointService {

    @Autowired
    private SAPEndpointRepository sapEndpointRepository;


    @Transactional(readOnly=true)
    public SAPEndpoint findBySAPEndpointIdId(Long sapEndpointId) {

        try
        {
            return sapEndpointRepository.findById(sapEndpointId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Transactional(readOnly=true)
    public SAPEndpoint findByTcpEndpointId(Long endpointId) {

        try
        {
            return sapEndpointRepository.findByTcpEndpointId(endpointId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Transactional(readOnly=true)
    public List<Node> findNodesByEndpointId(Long endpointId) {

        try
        {
            List<Node> nodeList = new ArrayList<>();
            SAPEndpoint sapEndpoint = sapEndpointRepository.findByTcpEndpointId(endpointId);
            Long srcNodeId = sapEndpoint.getSrcNodeId();
            Long snkNodeId = sapEndpoint.getSnkNodeId();
            return nodeList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
