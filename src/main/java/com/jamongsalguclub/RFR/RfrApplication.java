package com.jamongsalguclub.RFR;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.boot.SpringApplication;
/*
 * 지수에게
 * 세상의 모든 소하 보훈 유민 태수에게
 */
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class RfrApplication {
	public static void main(String[] args) {
        System.out.println("start!");
		SpringApplication.run(RfrApplication.class, args);
	}
}
