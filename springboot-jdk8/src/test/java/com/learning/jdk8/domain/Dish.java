package com.learning.jdk8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.learning.jdk8.domain
 * @Description:
 * @Author: Sammy
 * @Date: 2020/11/28 12:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
	private String name;
	private boolean vegetarian;
	private int calories;
	private Type type;
	public enum Type {MEAT, FISH, OTHER}
}
