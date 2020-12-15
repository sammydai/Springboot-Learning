package com.learning.helloworld.arraylist;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Package: com.learning.helloworld.arraylist
 * @Description: Arrays List Method Demo
 * @Author: Sammy
 * @Date: 2020/12/15 09:12
 */

@Slf4j
public class ArrayListDemo {

	private static List<List<Integer>> data = new ArrayList<>();

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	static class Order {
		private int orderId;
	}

	private static Object listSearch(int elementCount, int loopCount) {
		List<Order> list = IntStream.rangeClosed(1, elementCount).mapToObj(i -> new Order(i)).collect(toList());
		IntStream.rangeClosed(1,loopCount).forEach(i->{
			int search = ThreadLocalRandom.current().nextInt(elementCount);
			Order result = list.stream().filter(order -> order.getOrderId() == search).findFirst().orElse(null);
			Assert.assertTrue(result != null && result.getOrderId() == search);
		});
		return list;
	}

	private static Object mapSearch(int elementCount, int loopCount) {
		Map<Integer, Order> map = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toMap(Function.identity(), i -> new Order(i)));
		IntStream.rangeClosed(1, loopCount).forEach(i -> {
			int search = ThreadLocalRandom.current().nextInt(elementCount);
			Order result = map.get(search);
			Assert.assertTrue(result != null && result.getOrderId() == search);
		});
		return map;
	}

	/**
	 * 重要
	 */
	@Test
	public void testArrayList5() {
		int elementCount = 1000000;
		int loopCount = 1000;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("listSearch");
		Object list = listSearch(elementCount, loopCount);
		System.out.println(ObjectSizeCalculator.getObjectSize(list));
		stopWatch.stop();
		stopWatch.start("mapSearch");
		Object map = mapSearch(elementCount, loopCount);
		stopWatch.stop();
		System.out.println(ObjectSizeCalculator.getObjectSize(map));
		System.out.println(stopWatch.prettyPrint());
	}

	@Test
	public void oom() {
		for (int i = 0; i < 1000; i++) {
			List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(toList());
			data.add(rawList.subList(0, 1));
		}
	}

	@Test
	public void oom1() {
		List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
		List<Integer> subList = list.subList(1, 4);
		System.out.println(subList);
		subList.remove(1);
		System.out.println(list);
		list.add(0);
		try {
			subList.forEach(System.out::println);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void oomright1() {
		List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
		List<Integer> subList = new ArrayList<>(list.subList(1, 4));
		List<Integer> subList1 = list.stream().skip(1).limit(3).collect(toList());
		System.out.println(subList);
		subList.remove(1);
		System.out.println(list);
		list.add(0);
		subList.forEach(System.out::println);
	}

	@Test
	public void arrayListTest() {
		int[] arr = {1, 2, 3};
		List list = Arrays.asList(arr);
		List<Integer> list1 = Arrays.stream(arr).boxed().collect(toList());
		log.info("list:{} size:{} class:{}", list, list.size(), list.get(0).getClass());
		log.info("list1:{} size:{} class:{}", list1, list1.size(), list1.get(0).getClass());
	}

	@Test
	public void arrayListTest2() {
		int[] arr1 = {1, 2, 3};
		List<Integer> list1 = Arrays.stream(arr1).boxed().collect(toList());
		log.info("list1:{} size:{} class:{}", list1, list1.size(), list1.get(0).getClass());

		Integer[] arr2 = {1, 2, 3};
		List<Integer> list2 = Arrays.asList(arr2);
		log.info("list2:{} size:{} class:{}", list2, list2.size(), list2.get(0).getClass());
	}

	/**
	 *  Arrays.asList得到的结果，不是list类型，没有add方法
	 *  对原数据的改变，会影响Arrays.asList的结果
	 */
	@Test
	public void arrayListTest3() {
		String[] arr = {"1", "2", "3"};
		List list = Arrays.asList(arr);
		arr[1] = "4";
		try {
			list.add("5");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		log.info("arr:{} list:{}", Arrays.toString(arr), list);
	}
}



