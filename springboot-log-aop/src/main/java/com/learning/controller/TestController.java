package com.learning.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.learning.annotation.PermissionAnnotation;
import com.learning.service.AppService;
import com.learning.service.AppService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

/**
 * @description:
 * @author: Sammy
 * @time: 2020/2/3 01:17
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private AppService appService;

    @Autowired
    private AppService2 appService2;

    @GetMapping(value = "/test")
    public Dict test(String who) {
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }

    @GetMapping("/ioc")
    public void test1() {
        log.info("=======method1===============>");
        appService.show();
    }

    @GetMapping("/ioc2")
    public void test2() {
        log.info("========method2==============>");
        // appService2.show();
    }

    @PostMapping(value = "/postTest")
    public JSONObject aopTest2(@RequestParam("id") String id) {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @PostMapping("/check")
    @PermissionAnnotation()
    public JSONObject getRoundTest(@RequestBody JSONObject request) {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }
}
