package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OPENAI API에서 유저의 메시지로부터 추출한 키워드를 저장하는데 사용됩니다.<br><br>
 *
 * 추출한 키워드가 없을 때 keywordsZero가 true로 설정됩니다.<br>
 * 추출한 키워드들은 keywords 배열에 저장됩니다. keywordsZero가 true일 경우 빈 배열이 반환됩니다.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickedKeywords {
    private boolean keywordsZero;
    private String[] keywords;
}
