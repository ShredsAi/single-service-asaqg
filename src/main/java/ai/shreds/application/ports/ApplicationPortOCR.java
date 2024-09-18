package ai.shreds.application.ports;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for OCR operations within the application.
 * This interface provides methods for extracting text from PDF documents using OCR technology.
 */
public interface ApplicationPortOCR {

    /**
     * Extracts text from the given PDF document using OCR technology.
     * @param file the PDF file from which to extract text
     * @return the extracted text as a String
     */
    String extractTextFromPDF(MultipartFile file);
}