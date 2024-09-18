package ai.shreds.application.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ai.shreds.application.ports.ApplicationPortOCR;
import ai.shreds.infrastructure.external_services.InfrastructureServiceOCR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApplicationServiceOCR implements ApplicationPortOCR {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceOCR.class);
    private final InfrastructureServiceOCR ocrService;

    public ApplicationServiceOCR(InfrastructureServiceOCR ocrService) {
        this.ocrService = ocrService;
    }

    @Override
    public String extractTextFromPDF(MultipartFile file) {
        try {
            return ocrService.extractTextFromPDF(file);
        } catch (Exception e) {
            logger.error("Unexpected error during OCR processing for file: {}", file.getOriginalFilename(), e);
            throw new RuntimeException("Unexpected error during OCR processing", e);
        }
    }
}