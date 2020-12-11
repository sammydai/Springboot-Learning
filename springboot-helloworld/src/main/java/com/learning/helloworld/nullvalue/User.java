package com.learning.helloworld.nullvalue;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String name;
	private String nickname;
	private Integer age;
	private Date createDate = new Date();
}

