package ai.shreds.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;
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

    @Column(name = "fileName", nullable = false, length = 255)
    @Size(max = 255)
    private String fileName;

    @Column(name = "uploadedAt", nullable = false)
    private Date uploadedAt;

    @OneToMany(mappedBy = "documentId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DomainEntityExtractedData> extractedData = new ArrayList<>();
}