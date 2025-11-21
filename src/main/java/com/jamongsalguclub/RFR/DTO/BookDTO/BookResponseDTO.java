package com.jamongsalguclub.RFR.DTO.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BookDTO에 AI를 통해 추천하는 이유와 추천하는 책 내용의 요약, 책과 관련된 해시태그를 추가한 DTO입니다.<br><br>
 * OPENAPI의 책 추천 응답 반환용도 중 책의 정보와 및 유저에게 반환되는 값 중 책 정보를 표현하는데 사용됩니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private String title;
    private String link;
    private String image;
    private String author;
    private String discount;
    private String publisher;
    private String pubdate;
    private String isbn;
    private String description;
    private String reason;
    private String[] tag;
}
