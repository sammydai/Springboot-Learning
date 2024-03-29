package com.learning.actuator;

import com.learning.actuator.bean.TestApplicationContextInitializer;
import com.learning.actuator.clientdata.LoginRequiredArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class ActuatorApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ActuatorApplication.class);
		springApplication.addInitializers(new TestApplicationContextInitializer());
		// SpringApplication.run(ActuatorApplication.class, args);
		ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginRequiredArgumentResolver());
	}
}
