package com.learning.aop.service;

import org.springframework.stereotype.Service;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 16:44]
 */
@Service
public class TestService {
	public void query() {
		System.out.println("执行目标方法...");
	}
}

