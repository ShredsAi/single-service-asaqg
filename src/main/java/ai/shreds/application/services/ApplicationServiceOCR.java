package ai.shreds.application.services; 
  
 import org.springframework.stereotype.Service; 
 import org.springframework.web.multipart.MultipartFile; 
 import ai.shreds.application.ports.ApplicationPortOCR; 
 import ai.shreds.infrastructure.external_services.InfrastructureServiceOCR; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 /** 
  * Service implementation for OCR operations. 
  */ 
 @Service 
 public class ApplicationServiceOCR implements ApplicationPortOCR { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceOCR.class); 
     private final InfrastructureServiceOCR ocrService; 
  
     public ApplicationServiceOCR(InfrastructureServiceOCR ocrService) { 
         this.ocrService = ocrService; 
     } 
  
     /** 
      * Extracts text from the given PDF file using OCR. 
      * 
      * @param file the PDF file to extract text from 
      * @return the extracted text 
      */ 
     @Override 
     public String extractTextFromPDF(MultipartFile file) { 
         try { 
             return ocrService.extractTextFromPDF(file); 
         } catch (FileNotFoundException e) { 
             logger.error("File not found: {}", file.getOriginalFilename(), e); 
             throw new CustomFileNotFoundException("File not found: " + file.getOriginalFilename(), e); 
         } catch (OCRProcessingException e) { 
             logger.error("OCR processing error for file: {}", file.getOriginalFilename(), e); 
             throw new CustomOCRProcessingException("OCR processing error for file: " + file.getOriginalFilename(), e); 
         } catch (Exception e) { 
             logger.error("Unexpected error during OCR processing for file: {}", file.getOriginalFilename(), e); 
             throw new CustomUnexpectedException("Unexpected error during OCR processing", e); 
         } 
     } 
 } 
  
 /** 
  * Custom exception for file not found scenarios. 
  */ 
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class CustomFileNotFoundException extends RuntimeException { 
     private String message; 
     private Throwable cause; 
 } 
  
 /** 
  * Custom exception for OCR processing errors. 
  */ 
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class CustomOCRProcessingException extends RuntimeException { 
     private String message; 
     private Throwable cause; 
 } 
  
 /** 
  * Custom exception for unexpected errors during OCR processing. 
  */ 
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class CustomUnexpectedException extends RuntimeException { 
     private String message; 
     private Throwable cause; 
 }