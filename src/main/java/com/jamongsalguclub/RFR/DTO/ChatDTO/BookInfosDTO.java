package com.jamongsalguclub.RFR.DTO.ChatDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfosDTO {

    @JsonProperty("isOverZero")
    private boolean isOverZero;

    @JsonProperty("books")
    private BookItemsDTO[] books;
}
