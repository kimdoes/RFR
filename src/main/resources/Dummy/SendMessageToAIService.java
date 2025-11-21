/*
package com.jamongsalguclub.RFR.Dummy;

import com.fasterxml.jackson.core.JacksonException;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookInfosDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookItemsDTO;
import com.jamongsalguclub.RFR.DTO.HttpResponse.ChatHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class SendMessageToAIService {
    @Value("${openai.bef.prompt}")
    private String befPrompt;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;


    ResponseEntity<ChatHttpResponse> chat(String prompt) {
        try {
            BookInfosDTO bookItems = mapper.readValue(responseInString, BookInfosDTO.class);
            BookItemsDTO[] bookItemsList = bookItems.getBooks();

            ChatHttpResponse chatResponse = ChatHttpResponse.builder()
                    .code(200)
                    .message("성공")
                    .items(bookItemsList)
                    .build();

            return ResponseEntity.ok(chatResponse);
        } catch (JacksonException e) {
            ChatHttpResponse chatResponse = ChatHttpResponse.builder()
                    .code(500)
                    .message(e.getMessage())
                    .build();

            ResponseEntity<ChatHttpResponse> responseEntity = ResponseEntity.internalServerError().body(chatResponse);
            return ResponseEntity.ok(chatResponse);
        }
    }
}
 */