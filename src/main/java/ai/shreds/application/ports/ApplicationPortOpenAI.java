package ai.shreds.application.ports; 
  
 import ai.shreds.shared.SharedExtractedDataDTO; 
 import org.springframework.web.multipart.MultipartFile; 
  
 /** 
  * Interface for interacting with OpenAI APIs to identify specific fields from extracted text. 
  */ 
 public interface ApplicationPortOpenAI { 
     /** 
      * Identifies specific fields from the extracted text using OpenAI APIs. 
      * @param extractedText The text extracted from a PDF document. 
      * @return SharedExtractedDataDTO containing the identified data. 
      */ 
     SharedExtractedDataDTO identifyFieldsInText(String extractedText); 
 }