package com.learning.jdk8.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * @Package: com.dwt.jvm8.util
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/30 13:55
 */
@Slf4j
class EqualUtils {
	public static void main(String[] args) throws IOException {
		lombookExtend();
	}

	public static void lombookExtend(){
		Employee employee1 = new Employee("zhuye","001", "bkjk.com");
		Employee employee2 = new Employee("Joseph","002", "bkjk.com");
		log.info("employee1.equals(employee2) ? {}", employee1.equals(employee2));
	}

	public static void lombookTest(){
		Person person1 = new Person("zhuye","001");
		Person person2 = new Person("Joseph","001");
		log.info("person1.equals(person2) ? {}", person1.equals(person2));
	}

	public static void IntegerEquals(){
		Integer a = 127; //Integer.valueOf(127)
		Integer b = 127; //Integer.valueOf(127)
		log.info("\nInteger a = 127;\n" +
				"Integer b = 127;\n" +
				"a == b ? {}",a == b);    // true

		Integer c = 128; //Integer.valueOf(128)
		Integer d = 128; //Integer.valueOf(128)
		log.info("\nInteger c = 128;\n" +
				"Integer d = 128;\n" +
				"c == d ? {}", c == d);   //false

		Integer e = 127; //Integer.valueOf(127)
		Integer f = new Integer(127); //new instance
		log.info("\nInteger e = 127;\n" +
				"Integer f = new Integer(127);\n" +
				"e == f ? {}", e == f);   //false

		Integer g = new Integer(127); //new instance
		Integer h = new Integer(127); //new instance
		log.info("\nInteger g = new Integer(127);\n" +
				"Integer h = new Integer(127);\n" +
				"g == h ? {}", g == h);  //false

		Integer i = 128; //unbox
		int j = 128;
		log.info("\nInteger i = 128;\n" +
				"int j = 128;\n" +
				"i == j ? {}", i == j); //true
	}

	public static void testPointEqual(){
		// Point p1 = new Point(1, 2, "a");
		// Point p2 = new Point(1, 2, "b");
		// Point p3 = new Point(1, 2, "a");
		// log.info("p1.equals(p2) ? {}", p1.equals(p2));
		// log.info("p1.equals(p3) ? {}", p1.equals(p3));

		PointWrong p1 = new PointWrong(1, 2, "a");
		try {
			log.info("p1.equals(null)?{}",p1.equals(null));
		}catch (Exception e){
			e.printStackTrace();
		}

		try {
			Object o = new Object();
			log.info("p1.equals(expression)?{}",p1.equals(o));
		}catch (Exception e){
			e.printStackTrace();
		}

		PointWrong p2 = new PointWrong(1, 2, "b");
		log.info("p1.equals(p2) ? {}", p1.equals(p2));


		PointWrong p12 = new PointWrong(1, 2, "a");
		PointWrong p22 = new PointWrong(1, 2, "b");

		HashSet<PointWrong> points = new HashSet<>();
		points.add(p12);
		log.info("points.contains(p22) ? {}", points.contains(p22));
	}


	public static void wrong(){
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
}



class PointWrong  {
	private int x;
	private int y;
	private final String desc;


	public PointWrong(int x, int y, String desc) {
		this.x = x;
		this.y = y;
		this.desc = desc;
	}

	@Override
	public boolean equals(Object o) {
		PointWrong that = (PointWrong) o;
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {

		return Objects.hash(x, y);
	}
}


@Data
@AllArgsConstructor
@Slf4j
class Student implements Comparable<Student>{
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


@Data
@EqualsAndHashCode(callSuper = true)
class Employee extends Person {

	private String company;
	public Employee(String name, String identity, String company) {
		super(name, identity);
		this.company = company;
	}
}


@Data
class Person {

	@EqualsAndHashCode.Exclude
	private String name;

	private String identity;

	public Person(String name, String identity) {
		this.name = name;
		this.identity = identity;
	}
}