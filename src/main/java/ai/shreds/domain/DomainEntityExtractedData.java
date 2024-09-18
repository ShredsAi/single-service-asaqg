package ai.shreds.domain;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ExtractedData")
public class DomainEntityExtractedData {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "documentId", nullable = false)
    private UUID documentId;

    @Column(name = "siret", nullable = false)
    private String siret;

    @Column(name = "businessName", nullable = false)
    private String businessName;

    @Column(name = "businessAddress")
    private String businessAddress;

    @Column(name = "clientName", nullable = false)
    private String clientName;

    @Column(name = "clientAddress")
    private String clientAddress;

    @Column(name = "ceeIncitationGwhc", nullable = false)
    private BigDecimal ceeIncitationGwhc;

    @Column(name = "primeCeeEuros", nullable = false)
    private BigDecimal primeCeeEuros;

    @Column(name = "renovationNature", nullable = false)
    private String renovationNature;

    @ManyToOne
    @JoinColumn(name = "documentId", insertable = false, updatable = false)
    private DomainEntityDocument document;
}