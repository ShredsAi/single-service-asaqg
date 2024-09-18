package ai.shreds.adapter.primary;

import ai.shreds.shared.SharedUploadDocumentRequest;
import ai.shreds.shared.SharedUploadDocumentResponse;
import ai.shreds.shared.SharedExtractedDataResponse;
import ai.shreds.application.ports.ApplicationInputPortDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
public class AdapterDocumentController {

    private final ApplicationInputPortDocument applicationInputPortDocument;

    @Autowired
    public AdapterDocumentController(ApplicationInputPortDocument applicationInputPortDocument) {
        this.applicationInputPortDocument = applicationInputPortDocument;
    }

    @PostMapping("/upload")
    public ResponseEntity<SharedUploadDocumentResponse> uploadDocument(@RequestBody SharedUploadDocumentRequest request) {
        try {
            SharedUploadDocumentResponse response = applicationInputPortDocument.uploadDocument(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SharedUploadDocumentResponse(false, "Document upload failed due to an internal error.", null));
        }
    }

    @GetMapping("/{documentId}/data")
    public ResponseEntity<SharedExtractedDataResponse> getExtractedData(@PathVariable UUID documentId) {
        try {
            SharedExtractedDataResponse response = applicationInputPortDocument.getExtractedData(documentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new SharedExtractedDataResponse(documentId, null, null, null, null, null, null, null, null));
        }
    }
}