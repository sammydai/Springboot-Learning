package com.learning.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/25 14:24]
 */
@Setter
@Getter
@ToString(callSuper = true)
public class Cat extends Animal {

	private String name;
	private Integer age;
	private String color;

}

