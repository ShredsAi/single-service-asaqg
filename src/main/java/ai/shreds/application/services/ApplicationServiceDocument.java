package ai.shreds.application.services;

import ai.shreds.shared.SharedUploadDocumentRequest;
import ai.shreds.shared.SharedUploadDocumentResponse;
import ai.shreds.shared.SharedExtractedDataResponse;
import ai.shreds.application.ports.ApplicationInputPortDocument;
import ai.shreds.application.ports.ApplicationPortOCR;
import ai.shreds.application.ports.ApplicationPortOpenAI;
import ai.shreds.domain.ports.DomainPortDocumentRepository;
import ai.shreds.domain.ports.DomainPortExtractedDataRepository;
import ai.shreds.domain.DomainEntityDocument;
import ai.shreds.domain.DomainEntityExtractedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.util.UUID;
import java.util.List;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Pattern;

@Service
public class ApplicationServiceDocument implements ApplicationInputPortDocument {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceDocument.class);

    @Autowired
    private ApplicationPortOCR ocrService;
    @Autowired
    private ApplicationPortOpenAI openAIService;
    @Autowired
    private DomainPortDocumentRepository documentRepository;
    @Autowired
    private DomainPortExtractedDataRepository extractedDataRepository;

    @Override
    public SharedUploadDocumentResponse uploadDocument(SharedUploadDocumentRequest request) {
        try {
            logger.info("Starting document upload process");
            String extractedText = ocrService.extractTextFromPDF(request.getFile());
            DomainEntityExtractedData extractedData = openAIService.identifyFieldsInText(extractedText);

            if (!validateSiret(extractedData.getSiret()) || !validateBusinessName(extractedData.getBusinessName()) || !validateClientName(extractedData.getClientName()) || !validateExtractedData(extractedData)) {
                throw new IllegalArgumentException("Invalid data provided.");
            }

            DomainEntityDocument document = new DomainEntityDocument();
            document.setId(UUID.randomUUID());
            document.setFileName(request.getFile().getOriginalFilename());
            document.setUploadedAt(new Date());
            documentRepository.save(document);
            extractedData.setDocumentId(document.getId());
            extractedDataRepository.save(extractedData);

            logger.info("Document uploaded and processed successfully");
            return new SharedUploadDocumentResponse(true, "Document uploaded and processed successfully.", document.getId());
        } catch (Exception e) {
            logger.error("Failed to upload and process document", e);
            return new SharedUploadDocumentResponse(false, "Failed to upload and process document: " + e.getMessage(), null);
        }
    }

    @Override
    public SharedExtractedDataResponse getExtractedData(UUID documentId) {
        try {
            logger.info("Retrieving extracted data for document ID: " + documentId);
            List<DomainEntityExtractedData> extractedDataList = extractedDataRepository.findByDocumentId(documentId);
            if (extractedDataList.isEmpty()) {
                throw a RuntimeException("No extracted data found for document ID: " + documentId);
            }
            DomainEntityExtractedData extractedData = extractedDataList.get(0);
            return new SharedExtractedDataResponse(documentId, extractedData.getSiret(), extractedData.getBusinessName(), extractedData.getBusinessAddress(), extractedData.getClientName(), extractedData.getClientAddress(), extractedData.getCeeIncitationGwhc(), extractedData.getPrimeCeeEuros(), extractedData.getRenovationNature());
        } catch (Exception e) {
            logger.error("Failed to retrieve extracted data", e);
            throw new RuntimeException("Failed to retrieve extracted data: " + e.getMessage());
        }
    }

    private boolean validateSiret(String siret) {
        return siret != null && Pattern.matches("^[0-9A-Za-z]{14}$", siret);
    }

    private boolean validateBusinessName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean validateClientName(String name) {
        return name != null and !name.isEmpty();
    }

    private boolean validateExtractedData(DomainEntityExtractedData extractedData) {
        return extractedData.getCeeIncitationGwhc() != null && extractedData.getPrimeCeeEuros() != null && extractedData.getRenovationNature() != null;
    }
}