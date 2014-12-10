package com.bazinga.hakaton.service;

import java.io.InputStream;

/**
 * Created by Ivan on 23.11.2014.
 */
public interface PdfGeneratorService {

    InputStream generatePdf(Long subjectId);

}
