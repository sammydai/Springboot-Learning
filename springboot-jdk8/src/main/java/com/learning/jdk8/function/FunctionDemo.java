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

	public void lamdamethod() {
			// 类的静态方法
			Comparator<Integer> bb = Integer::compareTo;
			System.out.println(bb.compare(2, 3));
			Comparator<Integer> cc = (x, y) -> Integer.compare(x, y);
			System.out.println(cc.compare(56, 77));

			BiPredicate<String, String> bp = (x, y) -> x.equals(y);
			System.out.println("a -->b " + bp.test("a", "b"));

			BiPredicate<String, String> bp2 = String::equalsIgnoreCase;
			System.out.println("abc -->abc " + bp2.test("abc", "abc"));

			Consumer<String> con = x -> System.out.println(x + " test");
			con.accept("sss");

			Consumer<String> con1 = System.out::println;
			con1.accept("aaa");

			Emp emp = new Emp("sammy", 27);
			//无参数，无返回值的用法
			Supplier<String> getName = () -> emp.getName();
			System.out.println(getName.get());

			Supplier<Integer> getAge = emp::getAge;
			System.out.println(getAge.get());

			/*************** 构造器的引用 ****************/
			// 无参构造函数，创建实例
			Supplier<Emp> empSupplier = () -> new Emp();
			Emp emp1 = empSupplier.get();
			emp1.setAddress("Shanghai");
			System.out.println(emp1);

			Supplier<Emp> empSupplier2 = Emp::new;
			Emp emp2 = empSupplier2.get();
			emp2.setAddress("Beijing");
			emp2.setName("passed");
			System.out.println("emp2" + emp2);

			Function<String, Emp> function1 = (x) -> new Emp(x, 8);
			Emp london_city = function1.apply("london city");
			System.out.println(london_city);

			BiFunction<String, Integer, Emp> function2 = (x, y) -> new Emp(x, y);
			Emp xiaohong = function2.apply("xiaohong", 17);
			System.out.println(xiaohong);
		}
}
