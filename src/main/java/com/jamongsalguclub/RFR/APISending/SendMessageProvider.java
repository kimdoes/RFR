package com.jamongsalguclub.RFR.APISending;

import com.jamongsalguclub.RFR.DTO.ChatDTO.GPTRequest;
import com.jamongsalguclub.RFR.DTO.ChatDTO.GPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * GPT에게 실제로 메시지를 보내는 클래스입니다.<br>
 * 모든 클래스는 이 클래스를 통하여 CHAT-GPT에게 메시지를 보내게됩니다.
 */
@Component
class SendMessageProvider {
    private final RestTemplate restTemplate;

    /**
     * 사용할 GPT의 모델명입니다.
     */
    @Value("${openai.model}")
    private String model;

    /**
     * 요청을 보낼 OPENAI API 주소입니다.
     */
    @Value("${openai.api.url}")
    private String apiUrl;

    /**
     * OpenAiConfig 클래스에 등록된 restTemplate 객체를 스프링에서 주입받습니다.
     * @param restTemplate GPT에게 요청을 보내는데 사용할 RestTemplate 객체
     */
    private SendMessageProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * OPENAI API를 사용하여 실제로 메시지를 보내는 메서드입니다.
     *
     * @param content 유저에게 실제로 보낼 메시지입니다.
     * @param prompt 실제 메시지를 보내기 전, 사전설정을 위한 프롬프트입니다.
     * @return GPT의 답변이 String 형태로 주어집니다. <br>이 메서드를 호출하는 클래스들은 String으로 값을 반환받은 뒤
     * ObjectMapper를 통하여 각자 원하는 타입으로 형변환을 진행해야합니다.
     */
    String sendMessage(String content, String prompt) {
        GPTRequest request = new GPTRequest(model, content, prompt);
        GPTResponse response = restTemplate.postForObject(apiUrl, request, GPTResponse.class);

        return response.getChoices().get(0).getMessage().getContent();
    }
}
