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

	private String address;

	public Emp(String name, int age, Double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Emp(String address, String name, int age) {
		this.address = address;
		this.name = name;
		this.age = age;
	}

	public Emp(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Emp(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
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

	@Override
	public String toString() {
		return "Emp{" +
				"name='" + name + '\'' +
				", age=" + age +
				", salary=" + salary +
				", address='" + address + '\'' +
				'}';
	}
}
