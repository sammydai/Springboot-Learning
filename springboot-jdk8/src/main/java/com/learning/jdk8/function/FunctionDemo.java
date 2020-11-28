package com.learning.jdk8.function;

import com.learning.jdk8.domain.Emp;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.*;
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
}
