package com.jamongsalguclub.RFR.GetUserMessage;

import com.jamongsalguclub.RFR.DTO.BookDTO.BookResponseDTO;
import com.jamongsalguclub.RFR.DTO.ChatDTO.BookInfosDTO;
import com.jamongsalguclub.RFR.DTO.HttpRequest.ChatHttpRequestDTO;
import com.jamongsalguclub.RFR.DTO.HttpResponse.ChatHttpResponse;
import com.jamongsalguclub.RFR.APISending.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 컨트롤러
 */
@RestController
@RequestMapping("/books")
public class SendMessageToAIController {
    private final SendMessageService sendMessageService;
    @Autowired
    public SendMessageToAIController(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }



    @PostMapping
    public ResponseEntity<ChatHttpResponse> sendMessage(@RequestBody ChatHttpRequestDTO chatHttpRequestDTO) {
        String[] tags = {"얘기", "흥미", "재미"};

        BookResponseDTO b = BookResponseDTO.builder()
                .title("자몽살구클럽")
                .link("https://search.shopping.naver.com/book/catalog/55831245743")
                .image("https://shopping-phinf.pstatic.net/main_5583124/55831245743.20250722071159.jpg")
                .author("한로로")
                .discount("10800")
                .publisher("어센틱")
                .pubdate("2025")
                .isbn("9791199305304")
                .description("얘기")
                .reason("이유")
                .tag(tags)
                .build();

        BookResponseDTO b2 = BookResponseDTO.builder()
                .title("자몽살구클럽")
                .link("https://search.shopping.naver.com/book/catalog/55831245743")
                .image("https://shopping-phinf.pstatic.net/main_5583124/55831245743.20250722071159.jpg")
                .author("한로로")
                .discount("10800")
                .publisher("어센틱")
                .pubdate("2025")
                .isbn("9791199305304")
                .description("얘기")
                .reason("이유")
                .tag(tags)
                .build();

        BookResponseDTO[] brs = new BookResponseDTO[5];
        brs[0] = b;
        brs[1] = b2;
        /*
        private String link;
        private String image;
        private String author;
        private String discount;
        private String publisher;
        private String pubdate;
        private String isbn;
        private String description;
        private String reason;
        private String[] tag;
         */

        BookInfosDTO bookInfosDTO = BookInfosDTO.builder()
                .booksZero(false)
                .books(brs)
                .build();

        ChatHttpResponse chatHttpResponse = ChatHttpResponse.builder()
                .code(200)
                .message("성공")
                .items(bookInfosDTO)
                .build();

        String message = chatHttpRequestDTO.getMessage();
        System.out.println(message);
        return ResponseEntity.ok(chatHttpResponse);
        //return sendMessageService.sendMessage(message);
    }
}
