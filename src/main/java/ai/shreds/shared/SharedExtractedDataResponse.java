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
    private String businessName;

    /**
     * Business address extracted from the document.
     */
    private String businessAddress;

    /**
     * Client name extracted from the document.
     */
    private String clientName;

    /**
     * Client address extracted from the document.
     */
    private String clientAddress;

    /**
     * Amount of GWHc detected in the document.
     */
    private BigDecimal ceeIncitationGwhc;

    /**
     * Amount of Prime CEE in euros detected in the document.
     */
    private BigDecimal primeCeeEuros;

    /**
     * Nature of renovation mentioned in the document.
     */
    private String renovationNature;
}