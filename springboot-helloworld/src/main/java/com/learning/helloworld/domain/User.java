package com.learning.helloworld.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Package: com.learning.helloworld.domain
 * @Description: User Domain
 * @Author: Sammy
 * @Date: 2020/12/8 12:52
 */
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String name;
	private String nickname;
	private Integer age;
	private Date createDate = new Date();
}

