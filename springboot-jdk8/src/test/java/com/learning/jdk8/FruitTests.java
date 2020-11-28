package com.learning.jdk8;

import com.learning.jdk8.domain.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Package: com.learning.jdk8
 * @Description: Fruit Tests
 * @Author: Sammy
 * @Date: 2020/11/28 13:17
 */

public class FruitTests {
	List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
			new Apple(155, "green"),
			new Apple(120, "red"),
			new Apple(230, "red"));

	@Test
	public void applemethod() {
		List<Apple> redApples = inventory.stream().filter(apple -> apple.getColor().equals("red")).collect(toList());
		// System.out.println("redApples: "+redApples);
		List<Apple> redHeavyApples = inventory.stream().filter(apple -> apple.getColor().equals("red")).filter(apple -> apple.getWeight()>150).collect(toList());
		System.out.println("redHeavyApples: "+redHeavyApples);
		Comparator<Apple> c = Comparator.comparing(Apple::getWeight);

	}
}
