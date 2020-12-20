package com.learning.pattern.domain;

import lombok.Data;

/**
 * @Package: com.learning.pattern.domain
 * @Description: CreateUserAPI
 * @Author: Sammy
 * @Date: 2020/12/18 15:11
 */
@Data
public class CreateUserAPI {
	private String name;
	private String identity;
	private String mobile;
	private int age;
}
