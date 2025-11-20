package com.jamongsalguclub.RFR.ValidatBooks;

import com.jamongsalguclub.RFR.DTO.BookValidationDTO.BookValidationDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookItemsDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class ValidateBooks {
    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Value("${naver.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    private static BookItemsDTO[] getResultArray() {
        BookItemsDTO[] result = new BookItemsDTO[3];
        return result;
    }


    @PostConstruct
    public void init() {
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("X-Naver-Client-Id", clientId);
            request.getHeaders().add("X-Naver-Client-Secret", clientSecret);
            request.getHeaders().add("User-Agent", "Mozilla/5.0");
            return execution.execute(request, body);
        });
    }

    public BookItemsDTO[] validateBooks(BookItemsDTO[] bookItems) {
        int reulstidx = 0;
        BookItemsDTO[] result = new BookItemsDTO[3];

        for (BookItemsDTO bookItem : bookItems) {

            if (reulstidx == 3) {
                break;
            }

            if (bookItem == null) continue; // null이면 건너뛰기
            String bookName = bookItem.getBookName();
            String bookWriter = bookItem.getWriter();

            String url = apiUrl + bookName;
            BookValidationDTO bookValidationDTO = restTemplate.getForObject(url, BookValidationDTO.class);

            if (checkBooksInAPI(bookValidationDTO, bookWriter)) {
                result[reulstidx] = bookItem;
                reulstidx++;
            }
        }

        return result;
    }

    boolean checkBooksInAPI(BookValidationDTO bookValidationDTO, String bookWriter){
        for (int idx = 0; idx < bookValidationDTO.getDisplay(); idx++){

            if (bookWriter.equals(bookValidationDTO.getItems()[idx].getAuthor())){
                return true;
            } else {
                if (idx == bookValidationDTO.getDisplay()-1){
                    return false;
                }
            }
        }

        return false;
    }
}

/*
GPTRequest request = new GPTRequest(model, content, prompt);
        GPTResponse response = restTemplate.postForObject(apiUrl, request, GPTResponse.class);

        return response.getChoices().get(0).getMessage().getContent();
 */