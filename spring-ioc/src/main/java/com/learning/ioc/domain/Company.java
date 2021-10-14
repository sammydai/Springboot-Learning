package com.learning.ioc.domain;

/**
 * @Package: com.learning.ioc.domain
 * @Description: Company Domain
 * @Author: Sammy
 * @Date: 2021/7/22 15:06
 */

public class Company {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company{" +
				"name='" + name + '\'' +
				'}';
	}
}
