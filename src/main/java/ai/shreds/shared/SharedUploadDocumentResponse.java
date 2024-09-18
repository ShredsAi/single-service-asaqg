package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
  
 /** 
  * SharedUploadDocumentResponse is a DTO that encapsulates the response data for document upload requests. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedUploadDocumentResponse { 
     /** 
      * Indicates whether the document upload was successful. 
      */ 
     private Boolean success; 
  
     /** 
      * Provides feedback or error information about the upload process. 
      */ 
     private String message; 
  
     /** 
      * Unique identifier of the uploaded document. 
      */ 
     private UUID documentId; 
 }