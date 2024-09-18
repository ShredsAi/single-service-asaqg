package ai.shreds.infrastructure.external_services; 
  
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.multipart.MultipartFile; 
 import ai.shreds.application.ports.ApplicationPortOCR; 
 import net.sourceforge.tess4j.Tesseract; 
 import net.sourceforge.tess4j.TesseractException; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 import java.io.IOException; 
  
 @Service 
 public class InfrastructureServiceOCR implements ApplicationPortOCR { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureServiceOCR.class); 
  
     private final Tesseract tesseract; 
  
     public InfrastructureServiceOCR(@Value("${tesseract.datapath}") String tessDataPath, @Value("${tesseract.language}") String language) { 
         this.tesseract = new Tesseract(); 
         this.tesseract.setDatapath(tessDataPath); 
         this.tesseract.setLanguage(language); 
     } 
  
     @Override 
     public String extractTextFromPDF(MultipartFile file) { 
         try { 
             if (file.isEmpty()) { 
                 throw new IllegalArgumentException("The provided file is empty."); 
             } 
             if (!file.getContentType().equals("application/pdf")) { 
                 throw new IllegalArgumentException("Invalid file type. Only PDF files are supported."); 
             } 
             byte[] data = file.getBytes(); 
             String extractedText = tesseract.doOCR(data); 
             if (extractedText == null || extractedText.isEmpty()) { 
                 throw new RuntimeException("No text extracted from the provided PDF document."); 
             } 
             logger.info("Successfully extracted text from the provided PDF document."); 
             return extractedText; 
         } catch (TesseractException e) { 
             logger.error("Failed to extract text from the provided PDF document", e); 
             throw new RuntimeException("Failed to extract text from the provided PDF document", e); 
         } catch (IOException e) { 
             logger.error("Error reading the provided PDF file", e); 
             throw new RuntimeException("Error reading the provided PDF file", e); 
         } 
     } 
 }