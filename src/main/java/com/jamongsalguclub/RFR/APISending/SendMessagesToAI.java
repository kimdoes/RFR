package com.jamongsalguclub.RFR.APISending;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamongsalguclub.RFR.DTO.BookDTO.BookDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookInfosDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.PickedKeywords;
import com.jamongsalguclub.RFR.DTO.ChatDTO.RecommendBooksDTO;
import com.jamongsalguclub.RFR.DTO.HttpResponse.ChatHttpResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 유저와 네이버책검색API, OPENAI API의 중개자 역할을 맡은 클래스입니다.<br><br>
 * 해당 클래스에서는 유저의 요청에 토대로 AI에게 키워드를 반환하도록 요청을 전송하는 메서드,<br>
 * 키워드를 기반으로 네이버 책 검색API를 사용하여 '책 추천 후보군'을 반환하는 메서드,<br>
 * '책 추천 후보군'에서 AI에게 유저의 질문과 후보군을 토대로 가장 추천할만한 책을 추천하도록 요청하는 메서드로 이루어져있습니다.<br><br>
 * 모든 메서드들은 SendMessageService를 통해서만 접근가능하며, 해당 메서드들은 SendMessageService 클래스를 통해
 * 일련의 과정으로 실행됩니다.
 */
@Component
class SendMessagesToAI {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final SendMessageProvider sendMessageProvider;
    private final SendingBookApiProvider sendingBookApiProvider;
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * AI에게 보낼 프롬프트 메시지입니다.<br><br>
     * 해당 프롬프트는 책 추천 후보군과 유저의 메시지를 전달하여 최종적으로 책을 추천받는데 사용되는 AI 학습용 프롬프트입니다.
     */
    @Value("${openai.bef.prompt}")
    private String prompt;

    /**
     * AI에게 보낼 프롬프트 메시지입니다.<br><br>
     * 해당 프롬프트는 유저의 메시지를 토대로 메시지와 관련된 키워드를 추출하는데 사용되는 AI 학습용 프롬프트입니다.
     */
    @Value("${openai.checking.prompt}")
    private String checkPrompt;


    SendMessagesToAI(SendMessageProvider sendMessageProvider,
                     SendingBookApiProvider sendingBookApiProvider) {
        this.sendMessageProvider = sendMessageProvider;
        this.sendingBookApiProvider = sendingBookApiProvider;
    }


    /**
     * 유저의 메시지를 토대로 2~3개의 키워드를 추출하는 메서드입니다.<br>
     * ObjectMapper 객체의 .readValue() 메서드에서 오류가 발생할 수 있습니다.
     *
     * @param message 유저가 입력한 메시지입니다.
     * @return AI가 선정한 키워드를 담은 PickedKeywords 객체가 반환됩니다.
     */
    PickedKeywords PickKeywords(String message) {
        try {
            String keywords = sendMessageProvider.sendMessage(checkPrompt, message);
            PickedKeywords pickedKeywords = objectMapper.readValue(keywords, PickedKeywords.class);

            return pickedKeywords;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 해당 키워드와 유저의 메시지를 토대로 최대 15권의 책으로 이루어진 책 추천 후보군을 반환받는 메서드입니다.
     *
     * @param pickedKeywords 위의 PickKeywords 메서드를 통하여 반환된 키워드들입니다.
     * @param message 유저가 입력한 메시지입니다.
     * @return ResponesEntity가 반환됩니다. 내부에는 응답코드(code), 응답메시지(message),
     * 추천할 책의 배열(books)을 담고있는 DTO가 반환됩니다. <br>추천할 책이 없으면 null이 반환됩니다. (수정예정)
     */
    ResponseEntity<ChatHttpResponse> PickBooks(PickedKeywords pickedKeywords, String message) {

        if (pickedKeywords.isKeywordsZero()) {
            ChatHttpResponse response = ChatHttpResponse.builder()
                    .code(500)
                    .message("추천되는 아이템이 없습니다!")
                    .build();

            return ResponseEntity.ok(response);
        } else {
            try {
                String[] tags = pickedKeywords.getKeywords();
                BookDTO[] response = sendingBookApiProvider.SearchBooks(tags);

                RecommendBooksDTO recommendBooksDTO = RecommendBooksDTO.builder()
                        .userQuestion(message)
                        .books(response)
                        .build();

                String recommendedBooksAndQuestions = objectMapper.writeValueAsString(recommendBooksDTO);
                String recommendedBooksInString = sendMessageProvider.sendMessage(prompt, recommendedBooksAndQuestions);

                BookInfosDTO bookInfos = objectMapper.readValue(recommendedBooksInString, BookInfosDTO.class);

                if (!bookInfos.isBooksZero()) {
                    ChatHttpResponse responseForOb = ChatHttpResponse.builder()
                            .code(500)
                            .message("추천되는 아이템이 없습니다!")
                            .build();

                    return ResponseEntity.ok(responseForOb);
                }

                ChatHttpResponse chatHttpResponse = ChatHttpResponse.builder()
                        .code(200)
                        .message("성공")
                        .items(bookInfos)
                        .build();

                return ResponseEntity.ok(chatHttpResponse);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}