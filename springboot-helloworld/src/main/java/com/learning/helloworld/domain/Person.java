package com.learning.helloworld.domain;

import java.io.Serializable;
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return age == person.age &&
				Objects.equals(name, person.name) &&
				Objects.equals(job, person.job) &&
				Objects.equals(address, person.address);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, age, job, address);
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
