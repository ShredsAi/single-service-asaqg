package ai.shreds.domain.services;

import ai.shreds.domain.DomainEntityExtractedData;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DomainServiceDocumentProcessing {

    public boolean validateSiret(String siret) {
        return siret != null && siret.matches("\\d{14}");
    }

    public boolean validateBusinessName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean validateClientName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean validateCeeIncitationGwhc(BigDecimal ceeIncitationGwhc) {
        return ceeIncitationGwhc != null && ceeIncitationGwhc.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean validatePrimeCeeEuros(BigDecimal primeCeeEuros) {
        return primeCeeEuros != null && primeCeeEuros.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean validateRenovationNature(String renovationNature) {
        return renovationNature != null && !renovationNature.trim().isEmpty();
    }

    public boolean validateUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateExtractedData(DomainEntityExtractedData extractedData) {
        if (!validateSiret(extractedData.getSiret())) return false;
        if (!validateBusinessName(extractedData.getBusinessName())) return false;
        if (!validateClientName(extractedData.getClientName())) return false;
        if (!validateCeeIncitationGwhc(extractedData.getCeeIncitationGwhc())) return false;
        if (!validatePrimeCeeEuros(extractedData.getPrimeCeeEuros())) return false;
        if (!validateRenovationNature(extractedData.getRenovationNature())) return false;
        if (extractedData.getBusinessAddress() != null && extractedData.getBusinessAddress().length() > 1000) return false;
        if (extractedData.getClientAddress() != null && extractedData.getClientAddress().length() > 1000) return false;
        if (!validateUUID(extractedData.getDocumentId().toString())) return false;
        return true;
    }
}