package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * OPENAI API에게 보낼 메시지를 담은 DTO입니다.<br><br>
 *
 * role은 메시지의 특성을 다룹니다.<br>
 * system이라면 프롬프트의 역할<br>
 * user이라면 OPENAI API에게 보낼 유저의 메시지<br>
 * assistant이라면 OPENAI API의 반환메시지입니다.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String role;
    private String content;
}
