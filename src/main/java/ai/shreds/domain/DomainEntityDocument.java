package ai.shreds.domain; 
  
 import lombok.Data; 
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import javax.persistence.Column; 
 import javax.persistence.OneToMany; 
 import java.util.Date; 
 import java.util.UUID; 
 import java.util.List; 
 import java.util.ArrayList; 
  
 @Data 
 @Entity 
 @Table(name = "Documents") 
 public class DomainEntityDocument { 
  
     @Id 
     @Column(name = "id", nullable = false) 
     private UUID id; 
  
     @Column(name = "file_name", nullable = false, length = 255) 
     private String file_name; 
  
     @Column(name = "uploaded_at", nullable = false) 
     private Date uploaded_at; 
  
     @OneToMany(mappedBy = "document_id") 
     private List<DomainEntityExtractedData> extractedData = new ArrayList<>(); 
  
     public DomainEntityDocument() {} 
  
     public DomainEntityDocument(UUID id, String file_name, Date uploaded_at) { 
         this.id = id; 
         this.file_name = file_name; 
         this.uploaded_at = uploaded_at; 
         this.extractedData = new ArrayList<>(); 
     } 
 }