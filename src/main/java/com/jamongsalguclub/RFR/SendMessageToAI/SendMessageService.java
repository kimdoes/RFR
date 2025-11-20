package com.jamongsalguclub.RFR.SendMessageToAI;

import com.jamongsalguclub.RFR.DTO.ChatDTO.PickedKeywords;
import com.jamongsalguclub.RFR.DTO.HttpResponse.ChatHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {
    private SendMessagesToAI sendUserMessageService;
    public SendMessageService(SendMessagesToAI sendUserMessageService) {
        this.sendUserMessageService = sendUserMessageService;
    }

    public ResponseEntity<ChatHttpResponse> sendMessage(String message){
        PickedKeywords pickedKeywords = sendUserMessageService.SendUserMessage(message);
        return sendUserMessageService.sendBooks(pickedKeywords);
    }
}
