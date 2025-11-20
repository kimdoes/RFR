package com.jamongsalguclub.RFR.SendMessageToAI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookInfosDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookItemsDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.PickedKeywords;
import com.jamongsalguclub.RFR.DTO.HttpResponse.ChatHttpResponse;
import com.jamongsalguclub.RFR.ValidatBooks.ValidateBooks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SendMessagesToAI {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final SendMessageProvider sendMessageProvider;
    private final ValidateBooks validateBooks;

    @Value("${openai.bef.prompt}")
    private String prompt;

    @Value("${openai.checking.prompt}")
    private String checkPrompt;

    SendMessagesToAI(SendMessageProvider sendMessageProvider,
                     ValidateBooks validateBooks) {
        this.sendMessageProvider = sendMessageProvider;
        this.validateBooks = validateBooks;
    }

    PickedKeywords SendUserMessage(String message) {
        try {
            String keywords = sendMessageProvider.sendMessage(checkPrompt, message);
            PickedKeywords pickedKeywords = objectMapper.readValue(keywords, PickedKeywords.class);

            return pickedKeywords;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<ChatHttpResponse> sendBooks(PickedKeywords pickedKeywords) {
        if (pickedKeywords.isKeywordsZero()) {
            ChatHttpResponse response = ChatHttpResponse.builder()
                    .code(500)
                    .message("추천되는 아이템이 없습니다!")
                    .build();

            return ResponseEntity.ok(response);
        } else {
            try {
                StringBuilder keywordsInString = new StringBuilder();
                String[] keywords = pickedKeywords.getKeywords();

                for (String keyword : keywords) {
                    keywordsInString.append(keyword).append(" ");
                }

                String books = sendMessageProvider.sendMessage(prompt, keywordsInString.toString());
                BookItemsDTO[] booksDTO = objectMapper.readValue(books, BookInfosDTO.class).getBooks();

                booksDTO = validateBooks.validateBooks(booksDTO);

                ChatHttpResponse response = ChatHttpResponse.builder()
                        .code(200)
                        .message("성공")
                        .items(booksDTO)
                        .build();

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
