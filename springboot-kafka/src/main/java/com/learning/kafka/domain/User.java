package com.learning.kafka.domain;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Package: com.learning.ioc.bean.domain
 * @Description: User Domain
 * @Author: Sammy
 * @Date: 2021/7/31 20:26
 */

public class User implements InitializingBean, Person, BeanNameAware {
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
		System.out.println("user constructor");
	}

	@PostConstruct
	public void init() {
		System.out.println("init method invoke");
	}

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("bean after properties");
	}

	@Override
	public void sayHello() {
		System.out.println("User say hello!");
	}

	@Override
	public void setBeanName(String beanName) {
		System.out.println("这里是setBeanName的方法：" + beanName + "==============");
	}
}
