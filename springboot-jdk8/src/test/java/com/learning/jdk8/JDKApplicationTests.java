package com.learning.jdk8;

import com.learning.jdk8.domain.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Package: com.learning.jdk8
 * @Description:
 * @Author: Sammy
 * @Date: 2020/11/27 21:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDKApplicationTests {
	private static List<Emp> list = new ArrayList<>();

	static {
		list.add(new Emp("xiaoHong1", 20, 1000.0));
		list.add(new Emp("xiaoHong2", 25, 2000.0));
		list.add(new Emp("xiaoHong3", 30, 3000.0));
		list.add(new Emp("xiaoHong4", 35, 4000.0));
		list.add(new Emp("xiaoHong5", 38, 5000.0));
		list.add(new Emp("xiaoHong6", 45, 9000.0));
		list.add(new Emp("xiaoHong7", 55, 10000.0));
		list.add(new Emp("xiaoHong8", 42, 15000.0));
	}

	public static void println(Stream<Emp> stream) {
		stream.forEach(emp->{
			System.out.println(String.format("名字：%s，年纪：%s，薪水：%s", emp.getName(), emp.getAge(), emp.getSalary()));
		});
	}

	@Test
	public void suppliermethod() {
		Supplier<Integer> integerSupplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return new Random().nextInt();
			}
		};
		System.out.println(integerSupplier.get());
		System.out.println("*******************");

		integerSupplier = () -> new Random().nextInt();
		System.out.println(integerSupplier.get());
		System.out.println("*******************");

	}

	/**
	 *  optional对象有需要Supplier接口的方法
	 *  orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
	 */
	@Test
	public void suppliermethod2(){
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		Optional<Integer> first = stream.filter(i -> i > 4).findFirst();
		System.out.println(first.orElse(1));
		System.out.println(first.orElse(7));
	}

	@Test
	public void predicatemethod() {
		Predicate<Integer> predicate = new Predicate<Integer>() {
			@Override
			public boolean test(Integer integer) {
				return integer > 5 ? true : false;
			}
		};
		System.out.println(predicate.test(6));
		System.out.println("********************");

		predicate = (t) -> t > 10;
		System.out.println(predicate.test(7));
	}

	@Test
	public void functionmethod() {
		Function<String, Integer> function = new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return s.length();
			}
		};
		Stream<String> stream = Stream.of("aaa", "bbbbb", "ccccccv");
		stream.map(function).forEach(System.out::println);
		System.out.println("**********************");
	}

	@Test
	public void streammethod() {
		List<Integer> collect = Stream.iterate(1, x -> ++x).limit(10).collect(Collectors.toList());
		System.out.println(collect);
	}

	@Test
	public void functionmethod2() {
		List<Emp> emps = Arrays.asList(new Emp("a",27), new Emp("b",39), new Emp("c",10));
		emps.stream().map(emp -> emp.getName()).forEach(System.out::println);
		System.out.println("*****************************");
		emps.stream().filter(emp -> emp.getName() == "a").forEach(System.out::println);
	}

	@Test
	public void othermethod() {
		Arrays.asList(1,2,3,3,2).stream().distinct().sorted().forEach(str -> System.out.println(str));
		//对list里的emp对象，取出薪水，并对薪水进行排序，然后输出薪水的内容，map操作，改变了Strenm的泛型
		list.stream().map(emp -> emp.getSalary()).sorted().forEach(System.out::println);
		// 根据emp的属性name，进行排序
		println(list.stream().sorted(Comparator.comparing(Emp::getName)));
		// 给年纪大于30岁的人，薪水提升1.5倍，并输出结果
		println(list.stream().filter(emp -> emp.getAge() > 30).peek(emp -> emp.setSalary(emp.getSalary() * 1.5)));
	}
}
