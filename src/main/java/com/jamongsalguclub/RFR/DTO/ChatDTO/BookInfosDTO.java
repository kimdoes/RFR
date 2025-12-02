package com.jamongsalguclub.RFR.DTO.ChatDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jamongsalguclub.RFR.DTO.BookDTO.BookResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OPENAI API 응답반환용도로 사용됩니다.<br><br>
 * booksZero가 true일 경우 추천되는 책이 없으며, 유저에게는 추천되는 책이 없다고 응답이 반환됩니다.<br>
 * booksZero가 false일 경우 추천되는 책이 있다는 뜻입니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInfosDTO {

    private boolean booksZero;

    private BookResponseDTO[] books;
}
