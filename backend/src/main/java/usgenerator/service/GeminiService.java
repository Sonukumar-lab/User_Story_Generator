package usgenerator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import usgenerator.config.GeminiConfig;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Autowired
    private GeminiConfig geminiConfig;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    // ✅ Only API call responsibility
    public String callGemini(String finalPrompt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> textPart = Map.of("text", finalPrompt);
            Map<String, Object> content = Map.of("parts", List.of(textPart));
            Map<String, Object> requestBody = Map.of("contents", List.of(content));

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(
                            geminiConfig.getApiUrl() + "?key=" + geminiConfig.getApiKey(),
                            entity,
                            String.class
                    );

            return extractText(response.getBody());

        } catch (Exception e) {
            throw new RuntimeException("Gemini API failed", e);
        }
    }

    private String extractText(String response) throws Exception {
        JsonNode root = mapper.readTree(response);
        return root
                .path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();
    }
}
