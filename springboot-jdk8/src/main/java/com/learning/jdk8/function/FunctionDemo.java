package com.learning.jdk8.function;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Package: com.learning.function
 * @Description: Function Demo
 * @Author: Sammy
 * @Date: 2020/11/27 20:53
 */

public class FunctionDemo {

	public void suppliermethod() {
		Supplier<Integer> integerSupplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return new Random().nextInt();
			}
		};
		System.out.println(integerSupplier.get());
	}

	/**
	 *  optional对象有需要Supplier接口的方法
	 *  orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
	 */
	public void suppliermethod2(){
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		Optional<Integer> first = stream.filter(i -> i > 4).findFirst();
		System.out.println(first.orElse(1));
		System.out.println(first.orElse(7));
	}

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

	public void streammethod() {
		List<Integer> collect = Stream.iterate(1, x -> ++x).limit(10).collect(Collectors.toList());
		System.out.println(collect);
	}
}
