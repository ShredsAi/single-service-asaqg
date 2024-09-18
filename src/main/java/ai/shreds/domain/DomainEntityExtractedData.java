package ai.shreds.domain; 
  
 import java.math.BigDecimal; 
 import java.util.UUID; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainEntityExtractedData { 
  
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