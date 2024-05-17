package com.learning.datasource.druid.controller;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/3/21 10:56]
 */
@RestController
public class TestController {

	@RequestMapping("/test")
	@Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
	public void insert() {

	}
}
