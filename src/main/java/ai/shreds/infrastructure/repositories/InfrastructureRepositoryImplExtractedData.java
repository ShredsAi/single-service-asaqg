package ai.shreds.infrastructure.repositories; 
  
 import ai.shreds.domain.ports.DomainPortExtractedDataRepository; 
 import ai.shreds.domain.DomainEntityExtractedData; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.persistence.TypedQuery; 
 import java.util.List; 
 import java.util.UUID; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Repository 
 @RequiredArgsConstructor 
 @Slf4j 
 public class InfrastructureRepositoryImplExtractedData implements DomainPortExtractedDataRepository { 
  
     @PersistenceContext 
     private final EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainEntityExtractedData extractedData) { 
         try { 
             if (extractedData.getId() == null) { 
                 entityManager.persist(extractedData); 
             } else { 
                 entityManager.merge(extractedData); 
             } 
         } catch (Exception e) { 
             log.error("Error saving extracted data: ", e); 
             throw new RuntimeException("Failed to save extracted data", e); 
         } 
     } 
  
     @Override 
     public List<DomainEntityExtractedData> findByDocumentId(UUID documentId) { 
         try { 
             TypedQuery<DomainEntityExtractedData> query = entityManager.createQuery( 
                 "SELECT e FROM DomainEntityExtractedData e WHERE e.document_id = :documentId", DomainEntityExtractedData.class); 
             query.setParameter("documentId", documentId); 
             return query.getResultList(); 
         } catch (Exception e) { 
             log.error("Error finding extracted data by document ID: ", e); 
             throw new RuntimeException("Failed to find extracted data by document ID", e); 
         } 
     } 
 }