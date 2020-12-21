package com.learning.helloworld.feignclient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.learning.helloworld.feignclient
 * @Description: Config Feign
 * @Author: Sammy
 * @Date: 2020/12/16 16:31
 */
@Configuration
@EnableFeignClients(basePackages = "com.learning.helloworld.feignclient")
public class Config {

}
