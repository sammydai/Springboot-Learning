package com.learning.aop.controller;

import com.learning.aop.service.SayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @Package: com.learning.aop.controller
 * @Description: Say Controller
 * @Author: Sammy
 * @Date: 2020/12/15 21:38
 */
@RestController
@Slf4j
public class SayController {

	@Autowired
	List<SayService> sayServiceList;

	@GetMapping("testsay")
	public void test() {
		log.info("====================");
		sayServiceList.forEach(SayService::say);
	}
}
