package com.jamongsalguclub.RFR.SendMessageToAI;

import com.jamongsalguclub.RFR.DTO.ChatDTO.GPTRequest;
import com.jamongsalguclub.RFR.DTO.ChatDTO.GPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SendMessageProvider {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String sendMessage(String prompt, String content) {
        GPTRequest request = new GPTRequest(model, content, prompt);
        GPTResponse response = restTemplate.postForObject(apiUrl, request, GPTResponse.class);

        return response.getChoices().get(0).getMessage().getContent();
    }
}
