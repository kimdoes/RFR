package com.jamongsalguclub.RFR.APISending;

import com.jamongsalguclub.RFR.DTO.ChatDTO.PickedKeywords;
import com.jamongsalguclub.RFR.DTO.HttpResponse.ChatHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 유저의 메시지를 받아 유저의 메시지를 기반으로 AI에게 요청을 보내 책을 최대 5권까지 추천받아 반환하는 클래스입니다.<br><br>
 *
 * SendMessageToAi 클래스에 접근하기 위해서는 반드시 이 클래스를 통해서 접근해야합니다.<br>
 * 컨트롤러는 이 클래스를 호출합니다.
 */
@Service
public class SendMessageService {
    private SendMessagesToAI sendUserMessageService;
    public SendMessageService(SendMessagesToAI sendUserMessageService) {
        this.sendUserMessageService = sendUserMessageService;
    }

    /**
     * 유저의 메시지 기반 책을 최대 5권 추천합니다.<br><br>
     *
     * @param message 유저가 입력한 메시지
     * @return SendMessageService.PickBooks의 리턴값과 형식이 동일합니다.<br>
     * ResponseEntity가 반환됩니다. 내부에는 응답코드(code), 응답메시지(message), 추천할 책의 배열(books)을 담고있는 DTO가 반환됩니다.
     * 추천할 책이 없으면 null이 반환됩니다. (수정예정)
     */
    public ResponseEntity<ChatHttpResponse> sendMessage(String message){
        PickedKeywords pickedKeywords = sendUserMessageService.PickKeywords(message);
        System.out.println(pickedKeywords + " " + message);
        return sendUserMessageService.PickBooks(pickedKeywords, message);
    }
}