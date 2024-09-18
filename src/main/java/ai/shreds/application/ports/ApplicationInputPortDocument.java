package ai.shreds.application.ports;

import ai.shreds.shared.SharedUploadDocumentRequest;
import ai.shreds.shared.SharedUploadDocumentResponse;
import ai.shreds.shared.SharedExtractedDataResponse;
import java.util.UUID;

/**
 * Interface for document-related application services.
 */
public interface ApplicationInputPortDocument {
    /**
     * Processes the uploading of a PDF document, extracts text using OCR, identifies fields using OpenAI, and stores the data.
     *
     * @param request the shared upload document request containing the PDF file
     * @return response indicating the result of the upload and processing
     */
    SharedUploadDocumentResponse uploadDocument(SharedUploadDocumentRequest request);

    /**
     * Retrieves extracted data for a specified document based on its ID.
     *
     * @param documentId the UUID of the document whose data is to be retrieved
     * @return response containing the extracted data
     */
    SharedExtractedDataResponse getExtractedData(UUID documentId);
}