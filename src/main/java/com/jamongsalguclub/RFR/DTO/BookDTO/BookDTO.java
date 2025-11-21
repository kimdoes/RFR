package com.jamongsalguclub.RFR.DTO.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 책 한 권의 정보를 담고 있는 DTO 파일입니다.<br>
 * 파일에서 책의 정보를 담을 시 이 DTO를 사용하게됩니다.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
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
