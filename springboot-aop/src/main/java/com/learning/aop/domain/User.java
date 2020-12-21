package com.learning.aop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @Package: com.learning.aop.domain
 * @Description: User
 * @Author: Sammy
 * @Date: 2020/12/17 15:33
 */
@Data
public class User {
	private String username;
	private Integer age;
	private Integer phone;
	@JsonIgnore
	private String email;
	private Date createtime;

	public User(String username, Integer age,Date createtime,String email) {
		super();
		this.username = username;
		this.age = age;
		this.createtime = createtime;
		this.email = email;
	}
}



