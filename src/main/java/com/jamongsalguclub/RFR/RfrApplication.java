package com.jamongsalguclub.RFR;

import com.jamongsalguclub.RFR.DTO.ChatDTO.BookItemsDTO;
import com.jamongsalguclub.RFR.ValidatBooks.ValidateBooks;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.print.Book;

@SpringBootApplication
public class RfrApplication {
    private final ValidateBooks validateBooks;
    public RfrApplication(ValidateBooks validateBooks) {
        this.validateBooks = validateBooks;
    }

	public static void main(String[] args) {
        System.out.println("start!");
		SpringApplication.run(RfrApplication.class, args);
	}

    /*
    @PostConstruct
    public void test(){
        BookItemsDTO[] bookItems = new BookItemsDTO[5];

        BookItemsDTO bookItemsDTO = BookItemsDTO.builder()
                .bookName("자몽살구클럽")
                .writer("한로로")
                .build();

        BookItemsDTO bookItemsDTO2 = BookItemsDTO.builder()
                .bookName("압록강은흐른다")
                .writer("이미륵")
                .build();

        BookItemsDTO bookItemsDTO3 = BookItemsDTO.builder()
                .bookName("기억")
                .writer("한로로")
                .build();

        BookItemsDTO bookItemsDTO4 = BookItemsDTO.builder()
                .bookName("기억")
                .writer("로이스 로리")
                .build();

        BookItemsDTO bookItemsDTO5 = BookItemsDTO.builder()
                .bookName("JVM")
                .writer("저우즈밍")
                .build();

        bookItems[0] = bookItemsDTO;
        bookItems[1] = bookItemsDTO2;
        bookItems[2] = bookItemsDTO3;
        bookItems[3] = bookItemsDTO4;
        bookItems[4] = bookItemsDTO5;


        BookItemsDTO[] book = validateBooks.validateBooks(bookItems);

        for (int idx =0; idx < book.length; idx++){
            if (book[idx] == null){
                continue;
            }
            System.out.println("찾았습니다!: " + book[idx].getBookName() + book[idx].getWriter());
        }
    }
     */
}
