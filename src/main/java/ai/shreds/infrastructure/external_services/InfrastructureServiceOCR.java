package ai.shreds.infrastructure.external_services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ai.shreds.application.ports.ApplicationPortOCR;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Service
public class InfrastructureServiceOCR implements ApplicationPortOCR {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureServiceOCR.class);

    private final PDFTextStripper pdfTextStripper;

    public InfrastructureServiceOCR() throws IOException {
        this.pdfTextStripper = new PDFTextStripper();
    }

    @Override
    public String extractTextFromPDF(@NotNull(message = "The file cannot be null") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("The provided file is empty or null.");
        }
        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            throw new IllegalArgumentException("Invalid file type. Only PDF files are supported.");
        }
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            String extractedText = pdfTextStripper.getText(document);
            if (extractedText == null || extractedText.isEmpty()) {
                throw new IllegalArgumentException("No text extracted from the provided PDF document.");
            }
            logger.info("Successfully extracted text from the provided PDF document.");
            return extractedText;
        } catch (IOException e) {
            logger.error("Error reading the provided PDF file", e);
            throw new IllegalArgumentException("Error reading the provided PDF file", e);
        }
    }
}