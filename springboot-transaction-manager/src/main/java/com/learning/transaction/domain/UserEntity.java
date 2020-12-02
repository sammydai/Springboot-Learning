package com.learning.transaction.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

/**
 * @Package: com.learning.transaction.domain
 * @Description: User Entity
 * @Author: Sammy
 * @Date: 2020/12/2 09:50
 */

@Data
@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	private String name;
	public UserEntity() { }
	public UserEntity(String name) { this.name = name; }
}
