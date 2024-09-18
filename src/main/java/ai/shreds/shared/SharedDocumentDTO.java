package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.Date; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) for transferring document data across different layers of the application. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedDocumentDTO { 
     /** 
      * Unique identifier for the document. 
      */ 
     private UUID id; 
  
     /** 
      * Name of the uploaded PDF file. 
      */ 
     private String file_name; 
  
     /** 
      * Timestamp when the document was uploaded. 
      */ 
     private Date uploaded_at; 
 }