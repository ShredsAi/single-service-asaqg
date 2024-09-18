package ai.shreds.domain.ports; 
  
 import ai.shreds.domain.DomainEntityExtractedData; 
 import java.util.List; 
 import java.util.UUID; 
  
 /** 
  * Interface for repository operations related to ExtractedData entities. 
  */ 
 public interface DomainPortExtractedDataRepository { 
  
     /** 
      * Saves the given extracted data entity to the database. 
      * 
      * @param extractedData the extracted data entity to be saved 
      */ 
     void save(DomainEntityExtractedData extractedData); 
  
     /** 
      * Finds all extracted data entries associated with a given document ID. 
      * 
      * @param documentId the UUID of the document 
      * @return a list of extracted data entities 
      */ 
     List<DomainEntityExtractedData> findByDocumentId(UUID documentId); 
 }