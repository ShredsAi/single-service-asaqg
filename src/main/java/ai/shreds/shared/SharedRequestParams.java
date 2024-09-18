package ai.shreds.shared; 
  
 import lombok.Data; 
 import java.util.Date; 
  
 /** 
  * SharedRequestParams is a DTO that encapsulates parameters for document upload requests. 
  * It includes the file name and the timestamp when the document was uploaded. 
  */ 
 @Data 
 public class SharedRequestParams { 
     private String file_name; 
     private Date uploaded_at; 
 }