package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.EducationalUnit;
import com.bazinga.hakaton.repository.EducationalUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
@Service
@Transactional
public class EducationalUnitServiceBean implements EducationalUnitService {

    @Autowired
    private EducationalUnitRepository educationalUnitRepository;

    @Override
    public List<EducationalUnit> getAllEducationalUnits() {
        return educationalUnitRepository.findAll();
    }
}
