package com.learning.helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

/**
 * @Package: com.learning.helloworld.domain
 * @Description: Student Right
 * @Author: Sammy
 * @Date: 2020/12/7 22:37
 */
@Data
@AllArgsConstructor
@Slf4j
public class StudentRight implements Comparable<StudentRight>{
	private int id;
	private String name;

	@Override
	public int compareTo(StudentRight other) {
		return Comparator.comparing(StudentRight::getName)
				.thenComparing(StudentRight::getId)
				.compare(this, other);
	}
}
