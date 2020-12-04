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
	public String name;
	public boolean vegetarian;
	public int calories;
	public Type type;
	public enum Type {MEAT, FISH, OTHER}
}
