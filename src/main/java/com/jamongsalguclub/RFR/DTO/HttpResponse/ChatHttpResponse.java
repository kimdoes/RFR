package com.jamongsalguclub.RFR.DTO.HttpResponse;

import com.jamongsalguclub.RFR.DTO.BookDTO.BookResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HttpResponse에 사용되는 DTO입니다.<br><br>
 * code는 HttpStatus, message에는 성공, 실패와 같은 반환메시지가 들어가며,<br>items에는 최종적으로 추천할 책들이 배열로 들어갑니다.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHttpResponse {
    int code;
    String message;
    BookResponseDTO[] items;
}
