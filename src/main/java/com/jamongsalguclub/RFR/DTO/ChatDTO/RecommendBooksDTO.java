package com.jamongsalguclub.RFR.DTO.ChatDTO;

import com.jamongsalguclub.RFR.DTO.BookDTO.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유저의 메시지와 네이버 책 검색 API를 통해 추출한 추천할 책 후보군을 OPENAI API에 요청을 보내는데 사용됩니다.<br><br>
 * 이 DTO가 OPENAI API의 요청으로 전송됩니다.
 * userQuestion에서 유저가 입력한 질문이, books에 추천할 책 후보군이 들어갑니다.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBooksDTO {
    private String userQuestion;
    private BookDTO[] books;
}