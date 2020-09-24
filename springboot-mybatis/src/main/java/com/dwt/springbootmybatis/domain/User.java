package com.dwt.springbootmybatis.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package: com.dwt.springbootmybatis.domain
 * @Description: User 测试类
 * @Author: Sammy
 * @Date: 2020/9/23 10:04
 */

public class User implements Serializable {

	private static final long serialVersionUID = 8537611339806053830L;
	private Integer id;
	private String username;
	private Integer age;
	private String address;
	private String dept;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", age=" + age +
				", address='" + address + '\'' +
				", dept='" + dept + '\'' +
				", createtime=" + createtime +
				'}';
	}

	public User() {
	}

	public User(String username, Integer age, String address, String dept, Date createtime) {
		this.username = username;
		this.age = age;
		this.address = address;
		this.dept = dept;
		this.createtime = createtime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	private Date createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
