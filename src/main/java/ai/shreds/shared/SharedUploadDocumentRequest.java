package ai.shreds.shared;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class SharedUploadDocumentRequest {
    private MultipartFile file;
}