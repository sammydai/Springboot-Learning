package com.learning.apidesign.apiversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @Package: com.learning.apidesign.apiversion
 * @Description: CommonMistakesApplication
 * @Author: Sammy
 * @Date: 2020/12/22 14:22
 */
@SpringBootApplication
@Slf4j
public class CommonMistakesApplication implements WebMvcRegistrations{

	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return new APIVersionHandlerMapping();
	}

	public static void main(String[] args) {
		SpringApplication.run(CommonMistakesApplication.class, args);
		log.info("api version start successfully");
	}
}
