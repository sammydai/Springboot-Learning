package com.dwt.redission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissionApplication.class);
    }
}
