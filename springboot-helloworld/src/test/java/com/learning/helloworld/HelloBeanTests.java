package com.learning.helloworld;

import com.learning.helloworld.config.BeanConfig;
import com.learning.helloworld.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @Package: com.learning.helloworld
 * @Description: Bean Lifecycle Tests
 * @Author: Sammy
 * @Date: 2020/12/7 11:04
 */
@Slf4j
public class HelloBeanTests {

	@Test
	public void testbean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		People people = context.getBean(People.class);
		System.out.println(people);
	}

	@Test
	public void equaltest() {
		HashSet<City> hashSet = new HashSet<>();
		City city1 = new City("Beijing", 100);
		City city2 = new City("Shanghai", 100);
		System.out.println("city1.equals(city2): " + city1.equals(city2));
		hashSet.add(city1);
		System.out.println("hashSet.contains(city2): " + hashSet.contains(city2));
	}

	@Test
	public void equalextendtest() {
		Town town1 = new Town("Beijing", 200, "chaoyang");
		Town town2 = new Town("Shanghai", 100, "chaoyang");
		System.out.println("town1.equals(town2): " + town1.equals(town2));
	}

	@Test
	public void compareStudent() {
		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "zhang"));
		list.add(new Student(2, "wang"));
		Student student = new Student(2, "li");

		log.info("ArrayList.indexOf");
		int index1 = list.indexOf(student);
		Collections.sort(list);
		log.info("Collections.binarySearch");
		int index2 = Collections.binarySearch(list, student);

		log.info("index1 = " + index1);
		log.info("index2 = " + index2);
	}

	@Test
	public void compareStudentRight() {
		List<StudentRight> list = new ArrayList<>();
		list.add(new StudentRight(1, "zhang"));
		list.add(new StudentRight(2, "wang"));
		StudentRight student = new StudentRight(2, "li");

		log.info("ArrayList.indexOf");
		int index1 = list.indexOf(student);
		Collections.sort(list);
		log.info("Collections.binarySearch");
		int index2 = Collections.binarySearch(list, student);

		log.info("index1 = " + index1);
		log.info("index2 = " + index2);
	}
}
