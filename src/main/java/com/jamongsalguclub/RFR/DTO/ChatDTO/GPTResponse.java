package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.*;

import java.util.List;

/**
 * OPENAI API에서 응답을 반환받을 때 사용되는 DTO 파일입니다.<br><br>
 * AI의 응답 중 메타데이터나 여러 필요없는 데이터를 제외하고<br>실제 메시지가 담긴 choice 부분만 매핑하여 가져오도록합니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTResponse {
    private List<Choice> choices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private int index;
        private MessageDTO message;
    }
}
