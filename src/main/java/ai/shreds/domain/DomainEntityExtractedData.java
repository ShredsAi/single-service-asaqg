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