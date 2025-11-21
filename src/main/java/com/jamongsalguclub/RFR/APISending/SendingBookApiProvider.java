package com.jamongsalguclub.RFR.APISending;

import com.jamongsalguclub.RFR.DTO.BookDTO.BookDTO;
import com.jamongsalguclub.RFR.DTO.BookDTO.BookDTOForAPISearch;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

 /**
 * 이 클래스는 네이버 책 검색 API를 활용하여 정해진 일련의 키워드를 입력받아 해당 키워드와 관련된 책을 저장하는 클래스입니다.
 * 키워드를 String 배열로 받아 해당 키워드와 관련된 책을 키워드 당 최대 3권까지 선택하여 배열로 반환합니다.
 *
 * @author String
 */
@Component
class SendingBookApiProvider {
     /**
      * 네이버API 클라이언트 id
      */
    @Value("${naver.client.id}")
    private String clientId;

     /**
      * 네이버API 클라이언트 비밀번호
      */
    @Value("${naver.client.secret}")
    private String clientSecret;

     /**
      * 네이버API 클라이언트 요청 URL
      */
    @Value("${naver.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

     /**
      * 코드 실행 전 네이버API 요청을 보내기위한 RestTemplate 설정입니다. <br>
      * RestTemplate의 헤더에 각각 클라이언트id와 클라이언트 비밀번호를 추가합니다.<br><br>      *
      * 이 클래스에서 RestTemplate 객체는 아래와 같이 설정된 객체를 사용하게됩니다.
      */
    @PostConstruct
    private void init() {
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("X-Naver-Client-Id", clientId);
            request.getHeaders().add("X-Naver-Client-Secret", clientSecret);
            return execution.execute(request, body);
        });
    }

     /**
      * 책과 관련된 키워드를 String 배열로 받아<br>
      * 각 키워드와 관련된 책을 네이버 책 검색 api를 이용하여 키워드 당 최대 5권씩 '추천 책 후보군'을 배열로 사용합니다.
      *
      * @param tags 키워드 배열입니다.
      * @return 책에 대한 정보를 담고있는 BookDTO의 배열이 반환됩니다.
      */
    BookDTO[] SearchBooks(String[] tags){
        BookDTO[] result = new BookDTO[15];
        int index = 0;

        for (String tag : tags) {
            String url = apiUrl + tag;
            BookDTOForAPISearch responsedBooks = restTemplate.getForObject(url, BookDTOForAPISearch.class);

            int len;
            if (responsedBooks.getItems().length <= 3) {
                len = responsedBooks.getItems().length;
            } else {
                len = 3;
            }

            for (int idx = 0; idx < len;  idx++) {
                result[index] = responsedBooks.getItems()[idx];
                index++;

                if (index >= 15) {
                    break;
                }
            }
        }

        return result;
    }
}
