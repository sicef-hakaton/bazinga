package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.Card;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
@Service
public class PdfGeneratorServiceBean implements PdfGeneratorService {

    @Autowired
    private SubjectService subjectService;

    @Override
    public InputStream generatePdf(Long subjectId) {
        List<Card> cardsBySubject = subjectService.getCardsBySubject(subjectId);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(getPdfContent(cardsBySubject));
        renderer.layout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            renderer.createPDF(outputStream);
            // Finishing up
            renderer.finishPDF();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        try {
            return new ByteArrayInputStream(outputStream.toByteArray());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    /*****************************************************************
     *  EVERY HACKATON SHOULD HAVE AT LEAST ONE VERY DIRTY METHOD!!  *
     *****************************************************************/
    private String getPdfContent(List<Card> cardsBySubject){
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html><head><title>Okreni me</title></head><body>");
        for (Card card : cardsBySubject){
            sb.append("<div style=\"margin: auto; width:70%; background-color: #eee; text-align:center;\">");
            sb.append("<div style=\"padding: 1px;\">");
            sb.append("<h4>");
            sb.append(i++);
            sb.append(". ");
            sb.append(card.getQuestion());
            sb.append("</h4>");
            sb.append("<p>");
            sb.append(card.getAnswer());
            sb.append("</p>");
            sb.append("</div></div><br/>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }
}
