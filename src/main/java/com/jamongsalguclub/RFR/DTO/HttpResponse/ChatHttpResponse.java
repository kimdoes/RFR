package com.jamongsalguclub.RFR.DTO.HttpResponse;

import com.jamongsalguclub.RFR.DTO.ChatDTO.BookInfosDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookItemsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHttpResponse {
    int code;
    String message;
    BookItemsDTO[] items;
}
