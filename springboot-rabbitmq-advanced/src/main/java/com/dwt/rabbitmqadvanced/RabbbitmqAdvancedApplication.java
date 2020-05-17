package com.dwt.rabbitmqadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description RabbitMQ测试
 * @Exception
 *
 */
@EnableSwagger2
@SpringBootApplication
public class RabbbitmqAdvancedApplication {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dwt.rabbitmqadvanced.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBootRabbitMQ")
                .description("api接口的文档整理，支持在线测试")
                .version("1.0")
                .build();
    }

    public static void main( String[] args ) {
        SpringApplication.run(RabbbitmqAdvancedApplication.class, args);
    }
}
