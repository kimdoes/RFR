package com.jamongsalguclub.RFR.DTO.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 네이버 책 검색 API 반환용 DTO입니다.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTOForAPISearch {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private BookDTO[] items;
}
