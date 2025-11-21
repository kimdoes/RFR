package com.jamongsalguclub.RFR.GetUserMessage;

import com.jamongsalguclub.RFR.DTO.HttpRequest.ChatHttpRequestDTO;
import com.jamongsalguclub.RFR.DTO.HttpResponse.ChatHttpResponse;
import com.jamongsalguclub.RFR.APISending.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 컨트롤러
 */
@RestController
@RequestMapping("/books")
public class SendMessageToAIController {
    private final SendMessageService sendMessageService;
    @Autowired
    public SendMessageToAIController(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @GetMapping
    public ResponseEntity<ChatHttpResponse> sendMessage(@RequestBody ChatHttpRequestDTO chatHttpRequestDTO) {
        String message = chatHttpRequestDTO.getMessage();
        return sendMessageService.sendMessage(message);
    }
}
