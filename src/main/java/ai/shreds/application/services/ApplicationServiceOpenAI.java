package ai.shreds.application.services; 
  
 import ai.shreds.shared.SharedExtractedDataDTO; 
 import ai.shreds.application.ports.ApplicationPortOpenAI; 
 import org.springframework.stereotype.Service; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import ai.shreds.infrastructure.external_services.InfrastructureServiceOpenAI; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 public class ApplicationServiceOpenAI implements ApplicationPortOpenAI { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceOpenAI.class); 
     private final InfrastructureServiceOpenAI openAIService; 
  
     @Autowired 
     public ApplicationServiceOpenAI(InfrastructureServiceOpenAI openAIService) { 
         this.openAIService = openAIService; 
     } 
  
     @Override 
     public SharedExtractedDataDTO identifyFieldsInText(String extractedText) { 
         try { 
             logger.info("Identifying fields in the extracted text."); 
             return openAIService.identifyFieldsInText(extractedText); 
         } catch (Exception e) { 
             logger.error("Error identifying fields in text: ", e); 
             throw new RuntimeException("Failed to identify fields in text.", e); 
         } 
     } 
 }