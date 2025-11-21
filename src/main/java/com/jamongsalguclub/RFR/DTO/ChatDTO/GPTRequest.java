package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * OPENAI API를 통해 요청을 보낼 시, 보낼 요청의 형식을 담는 DTO 파일입니다.<br><br>
 *
 * model 인자를 통해 GPT의 모델 설정을<br>
 * MessageDTO List를 통하여 OPENAI API에게 보낼 메시지가 들어갑니다.<br><br>
 *
 * 생성자에서는 메시지 보내기 전 사전 프롬프트와 유저에 입력한 메시지를 messages 리스트에 추가하여<br>
 * AI가 두 메시지를 참고하도록 설정합니다.
 */
@Data
public class GPTRequest {

    private String model;
    private List<MessageDTO> messages;

    public GPTRequest(String model, String content, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();

        this.messages.add(new MessageDTO("system", prompt));
        this.messages.add(new MessageDTO("user", content));
    }
}
