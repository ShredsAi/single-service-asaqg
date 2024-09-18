package ai.shreds.infrastructure.external_services; 
  
 import ai.shreds.application.ports.ApplicationPortOpenAI; 
 import ai.shreds.shared.SharedExtractedDataDTO; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.client.RestTemplate; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.http.HttpEntity; 
 import org.springframework.http.HttpHeaders; 
 import org.springframework.http.MediaType; 
  
 /** 
  * InfrastructureServiceOpenAI implements ApplicationPortOpenAI to interact with OpenAI API. 
  * The API key is securely managed using Spring Boot's @Value annotation. 
  */ 
 @Slf4j 
 @Service 
 public class InfrastructureServiceOpenAI implements ApplicationPortOpenAI { 
  
     private final RestTemplate restTemplate; 
     private final String openAiUrl = "https://api.openai.com"; 
  
     @Value("${openai.api.key}") 
     private String apiKey; 
  
     public InfrastructureServiceOpenAI(RestTemplate restTemplate) { 
         this.restTemplate = restTemplate; 
     } 
  
     @Override 
     public SharedExtractedDataDTO identifyFieldsInText(String extractedText) { 
         if (extractedText == null || extractedText.isEmpty()) { 
             throw new IllegalArgumentException("Extracted text cannot be null or empty"); 
         } 
  
         HttpHeaders headers = new HttpHeaders(); 
         headers.setContentType(MediaType.APPLICATION_JSON); 
         headers.setBearerAuth(apiKey); 
  
         HttpEntity<String> request = new HttpEntity<>(extractedText, headers); 
  
         try { 
             ResponseEntity<SharedExtractedDataDTO> response = restTemplate.postForEntity(openAiUrl + "/v1/identify", request, SharedExtractedDataDTO.class); 
  
             if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) { 
                 return response.getBody(); 
             } else { 
                 log.error("Failed to identify fields from OpenAI service: {}", response.getStatusCode()); 
                 throw new RuntimeException("Failed to identify fields from OpenAI service"); 
             } 
         } catch (Exception e) { 
             log.error("Error while calling OpenAI service", e); 
             throw new RuntimeException("Error while calling OpenAI service", e); 
         } 
     } 
 } 
  
 // Note: Ensure proper testing of the error handling mechanism.