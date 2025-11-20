package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickedKeywords {
    private boolean keywordsZero;
    private String[] keywords;
}
