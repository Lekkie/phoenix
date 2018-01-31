package com.avantir.phoenix.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.phoenix.model.SwitchKeyOrigDataElem;
import com.avantir.phoenix.repository.SwitchKeyOrigDataElemRepository;
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
public class SwitchKeyOrigDataElemService {

    @Autowired
    private SwitchKeyOrigDataElemRepository switchKeyOrigDataElemRepository;

    @Transactional(readOnly=false)
    public SwitchKeyOrigDataElem create(SwitchKeyOrigDataElem switchKeyOrigDataElem) {
        return switchKeyOrigDataElemRepository.save(switchKeyOrigDataElem);
    }

    @Transactional(readOnly=true)
    public SwitchKeyOrigDataElem findByID(Long switchKey) {

        try
        {
            return switchKeyOrigDataElemRepository.findById(switchKey);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    @Transactional(readOnly=true)
    public SwitchKeyOrigDataElem findByOriginalDataElement(String originalDataElement) {

        try
        {
            return switchKeyOrigDataElemRepository.findByOriginalDataElementAllIgnoringCase(originalDataElement);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
