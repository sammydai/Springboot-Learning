package com.learning.jdk8;

import com.learning.jdk8.domain.Dish;
import com.learning.jdk8.domain.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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
	private static List<Emp> list1 = new ArrayList<>();

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

	static {
		list1.add(new Emp("上海", "小名", 17));
		list1.add(new Emp("北京", "小红", 18));
		list1.add(new Emp("深圳", "小蓝", 19));
		list1.add(new Emp("广州", "小灰", 20));
		list1.add(new Emp("杭州", "小黄", 21));
		list1.add(new Emp("贵阳", "小白", 22));
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

	@Test
	public void optionmethod() {
		Arrays.asList("a", "b", "c");
		Emp emp = new Emp("xiaoMing", 11, "上海");
		Optional<Emp> optionalEmp = Optional.ofNullable(emp);
		System.out.println(optionalEmp.get().getAddress());
		Optional<Emp> op = Optional.ofNullable(null);
		System.out.println(op.orElse(emp).getAddress());
	}

	@Test
	public void lamdamethod() {
		// 类的静态方法
		Comparator<Integer> bb = Integer::compareTo;
		System.out.println(bb.compare(2, 3));
		Comparator<Integer> cc = (x,y) -> Integer.compare(x, y);
		System.out.println(cc.compare(56, 77));

		BiPredicate<String,String> bp = (x, y) -> x.equals(y);
		System.out.println("a -->b " + bp.test("a", "b"));

		BiPredicate<String, String> bp2 = String::equalsIgnoreCase;
		System.out.println("abc -->abc " + bp2.test("abc", "abc"));

		Consumer<String> con = x -> System.out.println(x+" test");
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

		Function<String, Emp> function1 = (x) -> new Emp(x,8);
		Emp london_city = function1.apply("london city");
		System.out.println(london_city);

		BiFunction<String, Integer, Emp> function2 = (x, y) -> new Emp(x, y);
		Emp xiaohong = function2.apply("xiaohong", 17);
		System.out.println(xiaohong);
	}

	@Test
	public void collectionmethod() {
		List<String> names = list1.stream().map(emp -> emp.getName()).collect(toList());
		Set<String> namesset = list1.stream().map(emp -> emp.getName()).collect(toSet());
		System.out.println(list1.stream().count());

		String[] arr ={"aa","ccc","sss"};
		String ss = Arrays.stream(arr).collect(joining());
		String ss1 = Arrays.stream(arr).collect(joining("|"));
		List<String> collect = Arrays.stream(arr).collect(toList());
		System.out.println(ss1);
		System.out.println(ss);
		System.out.println(collect);
		//求数组中的某个值为key，为value，返回Map对象
		Map<String, Emp> empMap = list1.stream().collect(toMap(Emp::getName, Function.identity()));
	}

}
