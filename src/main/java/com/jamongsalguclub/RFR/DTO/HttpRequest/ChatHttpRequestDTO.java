package com.jamongsalguclub.RFR.DTO.HttpRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * HTTP request에 사용되는 DTO입니다.<br>
 * String 형태의 message 변수만 있는 가장 기본적인 형태입니다.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatHttpRequestDTO {
    private String message;
}
