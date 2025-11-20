package com.jamongsalguclub.RFR.DTO.BookValidationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookValidationDTO {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private BooksInfoDTO[] items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BooksInfoDTO {
        private String title;
        private String link;
        private String image;
        private String author;
        private String discount;
        private String publisher;
        private String pubdate;
        private String isbn;
        private String description;
    }
}
