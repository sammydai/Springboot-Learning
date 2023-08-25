package com.learning.aop.controller;

import com.learning.aop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 16:48]
 */
@RestController
public class TestController {
	@Autowired
	TestService testService;

	@RequestMapping("/testaop")
	public String test() {
		testService.query();
		return "ok";
	}
}
