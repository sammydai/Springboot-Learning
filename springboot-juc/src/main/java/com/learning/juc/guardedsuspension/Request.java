package com.learning.juc.guardedsuspension;

/**
 * request类表示请求
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 12:04]
 */

public class Request {
	private final String name;

	public Request(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "[ Request " + name + " ]";
	}
}
