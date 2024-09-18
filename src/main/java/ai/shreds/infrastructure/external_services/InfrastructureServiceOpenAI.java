package ai.shreds.infrastructure.external_services;

import ai.shreds.application.ports.ApplicationPortOpenAI;
import ai.shreds.shared.SharedExtractedDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class InfrastructureServiceOpenAI implements ApplicationPortOpenAI {

    private final RestTemplate restTemplate;
    private static final String OPEN_AI_URL = "https://api.openai.com/v1/identify";

    @Value("${openai.api.key}")
    private String apiKey;

    public InfrastructureServiceOpenAI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SharedExtractedDataDTO identifyFieldsInText(String extractedText) {
        if (extractedText == null || extractedText.isEmpty()) {
            throw new IllegalArgumentException("Extracted text cannot be null or empty");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("text", extractedText);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<SharedExtractedDataDTO> response = restTemplate.postForEntity(OPEN_AI_URL, request, SharedExtractedDataDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            } else {
                log.error("Failed to identify fields from OpenAI service: {}", response.getStatusCode());
                throw new RuntimeException("Failed to identify fields from OpenAI service");
            }
        } catch (Exception e) {
            log.error("Error while calling OpenAI service", e);
            throw new RuntimeException("Error while calling OpenAI service", e);
        }
    }
}