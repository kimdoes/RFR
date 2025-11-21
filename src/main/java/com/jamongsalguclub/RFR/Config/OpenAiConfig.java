package com.jamongsalguclub.RFR.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * OPENAPI 요청을 보내기위해 RestTemplate 객체를 수정하여 빈에 등록하는 코드입니다.
 */
@Configuration
class OpenAiConfig {
    /**
     * OPENAPI의 api key입니다.
     */
    @Value("${openai.api.key}")
    private String apiKey;

    /**
     * RestTemplate 객체를 사전설정하여 빈에 등록합니다.<br>
     * 헤더에 apikey를 추가합니다.
     * @return 사전설정이 끝난 RestTemplate 객체가 반환됩니다.<br>
     * 생성자가 아닌 DI를 통해 스프링에서 RestTemplate 객체를 주입받으면 아래와 같은 설정을 따르게됩니다.
     */
    @Bean
    protected RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }

}
