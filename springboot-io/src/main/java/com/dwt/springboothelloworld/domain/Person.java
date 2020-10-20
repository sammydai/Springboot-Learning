package com.dwt.springboothelloworld.domain;

import java.io.Serializable;

/**
 * @Package: com.dwt.springboothelloworld.domain
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/18 14:29
 */

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private int age;
	private transient String job;
	private static String color ="BLUE";

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person() {
	}

	public Person(String name, int age, String job) {
		this.name = name;
		this.age = age;
		this.job = job;
	}


	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", job='" + job + '\'' +
				", color='"+color+'\''+
				", address='"+address+'\''+
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
}
