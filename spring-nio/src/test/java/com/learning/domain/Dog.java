package com.learning.domain;

import lombok.Data;
import lombok.ToString;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/25 14:31]
 */
@Data
@ToString(callSuper = true)
public class Dog extends Animal {

	private String name;
	private Integer age;
	private String address;

}