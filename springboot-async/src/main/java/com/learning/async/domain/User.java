package com.learning.async.domain;

/**
 * @Package: com.learning.async.domain
 * @Description: User Domain
 * @Author: Sammy
 * @Date: 2020/11/30 16:45
 */

public class User {
	private String name;
	private String blog;

	public User(String name, String blog) {
		this.name = name;
		this.blog = blog;
	}

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", blog='" + blog + '\'' +
				'}';
	}
}
