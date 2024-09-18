package ai.shreds.shared;

import lombok.Data;
import java.util.UUID;
import java.math.BigDecimal;

@Data
public class SharedExtractedDataDTO {
    private UUID id;
    private UUID documentId;
    private String siret;
    private String businessName;
    private String businessAddress;
    private String clientName;
    private String clientAddress;
    private BigDecimal ceeIncitationGwhc;
    private BigDecimal primeCeeEuros;
    private String renovationNature;
}