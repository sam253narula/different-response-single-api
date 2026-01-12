package com.example.controller;

import com.example.dto.ParagraphDto;
import com.example.service.ParagraphService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api")
public class ParagraphController {

    private final ParagraphService service;

    public ParagraphController(ParagraphService service) {
        this.service = service;
    }

    // One endpoint; choose output by `format`
    @GetMapping("/paragraph")
    public ResponseEntity<?> paragraph(
            @RequestParam(defaultValue = "json") String format
    ) {
        String text = service.getParagraph();

        if ("csv".equalsIgnoreCase(format)) {
            String csv = service.toCsv(text);

            return ResponseEntity.ok()
                    .contentType(new MediaType("text", "csv"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=paragraph.csv")
                    .body(csv);

//            return ResponseEntity.ok()
//                    contentType(new MediaType("text", "csv"))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=paragraph.csv")
//                    .body(csv);


        }

        if ("pdf".equalsIgnoreCase(format)) {
            ByteArrayInputStream pdfStream = service.toPdf(text);


            // If you use below return then use this curl command::
         /*curl -L \
          'http://localhost:8080/api/paragraph?format=pdf' \
          -o paragraph.pdf */
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=paragraph.pdf")
//                    .body(new InputStreamResource(pdfStream));


            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=paragraph.pdf")
                    .body(new InputStreamResource(pdfStream));

        }

        // default JSON
        ParagraphDto dto = new ParagraphDto(text);
        return ResponseEntity.ok(dto);
    }
}
