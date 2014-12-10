package com.bazinga.hakaton.rest;

import com.bazinga.hakaton.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ivan on 23.11.2014.
 */
@RestController
@RequestMapping("/pdf")
public class PdfResource {

    @Autowired
    private PdfGeneratorService pdfGenerator;

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable("id") Long subjectId, HttpServletResponse response) {
        try {
            InputStream is = pdfGenerator.generatePdf(subjectId);
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

}
