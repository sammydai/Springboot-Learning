package com.learning.helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

/**
 * @Package: com.learning.helloworld.domain
 * @Description: Student Compare
 * @Author: Sammy
 * @Date: 2020/12/7 22:28
 */
@Data
@AllArgsConstructor
@Slf4j
public class Student implements Comparable<Student>{
	private int id;
	private String name;

	@Override
	public int compareTo(Student other) {
		int result = Integer.compare(other.id, id);
		if (result==0)
			log.info("this {} == other {}", this, other);
		return result;
	}
}
