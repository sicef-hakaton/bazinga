package com.bazinga.hakaton.rest;

import com.bazinga.hakaton.dto.DtoTransformer;
import com.bazinga.hakaton.dto.EducationalUnitDto;
import com.bazinga.hakaton.service.EducationalUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ivan on 22.11.2014.
 */
@RestController
@RequestMapping("/educationalunit")
public class EducationalUnitResource {

    @Autowired
    private EducationalUnitService educationalUnitService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EducationalUnitDto> getAll() {
        return DtoTransformer.convert(educationalUnitService.getAllEducationalUnits());
    }


}
