package com.learning.jdk8.domain;

/**
 * @Package: com.learning.jdk8
 * @Description:
 * @Author: Sammy
 * @Date: 2020/11/27 23:44
 */

public class Emp {
	private String name;

	private int age;

	private Double salary;

	public Emp(String name, int age, Double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Emp{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public Emp(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Emp() {
		super();
	}

	public Emp(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
