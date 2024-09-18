package ai.shreds.shared; 
  
 import lombok.Data; 
 import java.util.UUID; 
 import java.math.BigDecimal; 
  
 @Data 
 public class SharedExtractedDataDTO { 
     private UUID id; 
     private UUID document_id; 
     private String siret; 
     private String business_name; 
     private String business_address; 
     private String client_name; 
     private String client_address; 
     private BigDecimal cee_incitation_gwhc; 
     private BigDecimal prime_cee_euros; 
     private String renovation_nature; 
 } 
 // Note: Include Lombok annotations for getters and setters.