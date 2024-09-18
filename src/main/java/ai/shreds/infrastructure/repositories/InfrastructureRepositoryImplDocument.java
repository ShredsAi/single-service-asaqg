package ai.shreds.infrastructure.repositories; 
  
 import ai.shreds.domain.ports.DomainPortDocumentRepository; 
 import ai.shreds.domain.DomainEntityDocument; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import lombok.extern.slf4j.Slf4j; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import java.util.UUID; 
  
 @Repository 
 @Slf4j 
 public class InfrastructureRepositoryImplDocument implements DomainPortDocumentRepository { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainEntityDocument document) { 
         if (document == null) { 
             throw new IllegalArgumentException("Document cannot be null"); 
         } 
         log.info("Saving document with ID: {}", document.getId()); 
         entityManager.persist(document); 
     } 
  
     @Override 
     public DomainEntityDocument findById(UUID documentId) { 
         if (documentId == null) { 
             throw new IllegalArgumentException("Document ID cannot be null"); 
         } 
         log.info("Finding document with ID: {}", documentId); 
         return entityManager.find(DomainEntityDocument.class, documentId); 
     } 
 }