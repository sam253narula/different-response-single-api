package com.example.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class ParagraphService {

    public String getParagraph() {
        return "This is a sample paragraph. It can be returned as JSON, CSV, or PDF "
                + "from the same endpoint depending on the 'format' parameter.";
    }

    public String toCsv(String paragraph) {
        // Very small CSV: header + single row, with basic quote escaping
        String escaped = paragraph.replace("\"", "\"\"");
        return "paragraph\n" + "\"" + escaped + "\"\n";
    }

    public ByteArrayInputStream toPdf(String paragraph) {
        com.lowagie.text.Document document = new com.lowagie.text.Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            com.lowagie.text.pdf.PdfWriter.getInstance(document, out);
            document.open();
            document.add(new com.lowagie.text.Paragraph(paragraph));
        } catch (com.lowagie.text.DocumentException e) {
            throw new RuntimeException("PDF generation failed", e);
        } finally {
            document.close();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
