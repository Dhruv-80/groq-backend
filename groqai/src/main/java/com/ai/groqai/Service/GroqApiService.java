package com.ai.groqai.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class GroqApiService {

    @Value("${groq.api.key}")
    private String groqApiKey;

    private final RestTemplate restTemplate;

    public GroqApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCompletion(String userMessage, String model) {
        String url = "https://api.groq.com/openai/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + groqApiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = String.format("{\"messages\": [{\"role\": \"user\", \"content\": \"%s\"}], \"model\": \"%s\"}", userMessage, model);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray choices = jsonResponse.getJSONArray("choices");
        JSONObject firstChoice = choices.getJSONObject(0);
        JSONObject message = firstChoice.getJSONObject("message");

        return message.getString("content");
    }
}
