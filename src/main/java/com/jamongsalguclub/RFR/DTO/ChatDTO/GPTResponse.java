package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTResponse {
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private int index;
        private MessageDTO message;
    }
}
