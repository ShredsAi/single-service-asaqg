package ai.shreds.shared; 
  
 import lombok.Data; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) for the response of extracted data from a document. 
  * This class encapsulates the fields that are identified and extracted from the uploaded PDF document. 
  */ 
 @Data 
 public class SharedExtractedDataResponse { 
     /** 
      * Unique identifier of the document. 
      */ 
     private UUID documentId; 
  
     /** 
      * Siret number extracted from the document. 
      */ 
     private String siret; 
  
     /** 
      * Business name extracted from the document. 
      */ 
     private String business_name; 
  
     /** 
      * Business address extracted from the document. 
      */ 
     private String business_address; 
  
     /** 
      * Client name extracted from the document. 
      */ 
     private String client_name; 
  
     /** 
      * Client address extracted from the document. 
      */ 
     private String client_address; 
  
     /** 
      * Amount of GWHc detected in the document. 
      */ 
     private BigDecimal cee_incitation_gwhc; 
  
     /** 
      * Amount of Prime CEE in euros detected in the document. 
      */ 
     private BigDecimal prime_cee_euros; 
  
     /** 
      * Nature of renovation mentioned in the document. 
      */ 
     private String renovation_nature; 
 } 
  
 // Note: Use Lombok annotations for getters and setters to reduce boilerplate code.