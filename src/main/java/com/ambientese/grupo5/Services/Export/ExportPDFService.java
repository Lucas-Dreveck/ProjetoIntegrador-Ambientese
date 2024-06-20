package com.ambientese.grupo5.Services.Export;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class ExportPDFService {

    @Autowired
    private TemplateEngine templateEngine;

    public ByteArrayInputStream generatePdf(String templateName, Map<String, Object> data) throws DocumentException, IOException {
        // Renderizar o template Thymeleaf
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);

        // Converter HTML para PDF usando iTextPDF e Flying Saucer
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
