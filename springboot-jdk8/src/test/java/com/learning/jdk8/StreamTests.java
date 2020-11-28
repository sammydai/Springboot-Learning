package com.learning.jdk8;

import com.learning.jdk8.domain.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Package: com.learning.jdk8
 * @Description: Stream Tests
 * @Author: Sammy
 * @Date: 2020/11/28 12:23
 */

public class StreamTests {
	List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH) );

	@Test
	public void streammethod() {
		//1 返回低热量的菜肴名称的，按照卡路里排序
		List<String> collect = menu.stream().filter(dish -> dish.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories)).map(dish -> dish.getName()).collect(toList());
		System.out.println(collect);
		//2 流只能消费一次！
		List<String> title = Arrays.asList("Java8", "In", "Action");
		Stream<String> stream = title.stream();
		stream.forEach(System.out::println);
		//3 获取所有菜肴名称 对流中每一个元素应用函数
		List<String> menuName = menu.stream().map(Dish::getName).collect(toList());
		System.out.println(menuName);
		//4 获取所有素食菜肴 用谓词筛选
		List<Dish> vegList = menu.stream().filter(Dish::isVegetarian).collect(toList());
		System.out.println(vegList);
		//5 筛选各异的元素（去重）
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream().filter(integer -> integer % 2 == 0).distinct().forEach(System.out::println);
		//6 截短流
		menu.stream().filter(dish -> dish.getCalories() > 300).limit(3).forEach(System.out::println);
		//7 跳过元素
		menu.stream().filter(dish -> dish.getCalories() > 300).skip(2).forEach(System.out::println);
		//8 检查谓词是否至少匹配一个元素
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
		//9 检查谓词是否匹配所有元素
		System.out.println(menu.stream().allMatch(dish -> dish.getCalories() < 1000));
		//10 查找元素
		Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
		dish.ifPresent(dish1 -> System.out.println(dish1.getName()));
		//11 查找出第一个元素
		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst().ifPresent(System.out::println);
		//12 元素求和
		Integer sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println("sum: "+sum);
		Optional<Integer> sum2 = someNumbers.stream().reduce((a, b) -> a + b);
		//13 最大值和最小值
		Optional<Integer> maxValue = someNumbers.stream().reduce(Integer::max);
		maxValue.ifPresent(System.out::println);
		Optional<Integer> minValue = someNumbers.stream().reduce(Integer::min);
		minValue.ifPresent(System.out::println);
		//14 卡路里求和
		int calsum = menu.stream().mapToInt(Dish::getCalories).sum();
		System.out.println("calsum: " + calsum);
		menu.stream().mapToInt(Dish::getCalories).max().ifPresent(System.out::println);
		//15 如果没有最大值的话，你就可以显式处理OptionalInt去定义一个默认值了：
		int minDish = menu.stream().mapToInt(Dish::getCalories).min().orElse(12);
		System.out.println("minDish: " + minDish);
		//16 数值范围
		System.out.println("count: "+IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).count());
	}

	@Test
	public void constreammethod() {
		//1 由值创建流
		Stream<String> ss = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
		ss.map(String::toUpperCase).forEach(System.out::println);
		Stream<Object> empty = Stream.empty();
		//2 由数组创建流
		int[] numbers = {2, 3, 5, 7, 11, 13};
		int numstream = Arrays.stream(numbers).sum();
		System.out.println("numstream: "+numstream);
		//3 由文件生成流
		//4 由函数生成流：创建无限流
		Stream.iterate(0, i -> i + 2).limit(10).forEach(System.out::println);
		Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);
	}
}
