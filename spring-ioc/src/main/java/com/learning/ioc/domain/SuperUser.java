package com.learning.ioc.domain;

import com.learning.ioc.annotation.Super;

/**
 * @Package: com.learning.ioc.domain
 * @Description: Super User Domain
 * @Author: Sammy
 * @Date: 2021/7/22 15:06
 */

@Super
public class SuperUser {
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "SuperUser{" +
				"address='" + address + '\'' +
				'}';
	}
}
