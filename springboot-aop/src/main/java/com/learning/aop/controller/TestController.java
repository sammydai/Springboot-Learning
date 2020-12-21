package com.learning.aop.controller;


import com.learning.aop.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 *@Package: TestController
 *@Description:
 *@Author: Sammy
 *@Date: 2020/12/21 10:56
 *
 **/

@Slf4j
@RequestMapping("test")
@RestController
public class TestController {
    @GetMapping
    public void test() {

    }

    @GetMapping("/user")
    public User testuser() {
        User user = new User("name", 18,new Date(),"ss@163.com");
        return user;
    }
}
