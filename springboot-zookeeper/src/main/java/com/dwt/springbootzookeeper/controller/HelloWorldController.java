package com.dwt.springbootzookeeper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.dwt.springbootzookeeper.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/21 19:29
 */

@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public String HelloWorld() {
        return "Hello World! HAHAHA";
    }
}

