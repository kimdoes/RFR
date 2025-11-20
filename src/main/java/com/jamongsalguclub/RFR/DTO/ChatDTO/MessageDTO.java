package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String role;
    private String content;
}
