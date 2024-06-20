package com.ambientese.grupo5.Controller.Export;

import com.ambientese.grupo5.Services.Export.ExportPDFService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
public class ExportPDFController {

    @Autowired
    private ExportPDFService exportPDFService;

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportPdf() throws DocumentException, IOException {
        // Dados para o template Thymeleaf (ajuste conforme necessário)
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Avaliação");
        data.put("content", "<h1>Conteúdo da Avaliação</h1><p>Este é um exemplo de conteúdo que será exportado para PDF.</p>");

        ByteArrayInputStream bis = exportPDFService.generatePdf("template", data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=avaliacao.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
