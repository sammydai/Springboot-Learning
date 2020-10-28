package com.dwt.jvm8.controller;


import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 *@description:
 *@author: Sammy
 *@time: 2020/2/3 01:17
 *
 */
@RestController
public class TestController {

    @GetMapping(value = "/test")
    public Dict test(String who) {
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }
}
