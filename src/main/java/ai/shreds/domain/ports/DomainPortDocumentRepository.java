package ai.shreds.domain.ports;

import ai.shreds.domain.DomainEntityDocument;
import java.util.UUID;

/**
 * Interface for Document Repository operations in the domain layer.
 */
public interface DomainPortDocumentRepository {

    /**
     * Saves a document to the database.
     * @param document The document entity to be saved.
     */
    void save(DomainEntityDocument document);

    /**
     * Finds a document by its UUID.
     * @param documentId The UUID of the document to find.
     * @return The found document or null if not found.
     */
    DomainEntityDocument findById(UUID documentId);

    /**
     * Deletes a document by its UUID.
     * @param documentId The UUID of the document to delete.
     */
    void deleteById(UUID documentId);
}