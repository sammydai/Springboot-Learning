package com.learning.jdk8;

import com.learning.jdk8.domain.Order;
import com.learning.jdk8.domain.OrderItem;
import com.learning.jdk8.domain.Product;
import javafx.scene.layout.BorderStrokeStyle;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @Package: com.learning.jdk8
 * @Description: Stream Test Advanced
 * @Author: Sammy
 * @Date: 2020/12/30 15:24
 */

public class StreamTestAdvanced {

	private List<Order> orders;

	@Before
	public void init() {
		orders = Order.getData();
	}

	@Test
	public void streamof() {
		String[] arr = {"a", "b", "c"};
		Stream.of(arr).forEach(System.out::println);
		Stream.of("a","bb","ccc").forEach(System.out::println);
	}

	@Test
	public void stream() {
		Arrays.asList("a","b","c").stream().forEach(System.out::println);
		Arrays.stream(new int[]{1,2,3}).forEach(System.out::println);
	}

	@Test
	public void iterate() {
		Stream.iterate(2,item->item*2).limit(10).forEach(System.out::println);
		Stream.iterate(BigInteger.ZERO,item->item.add(BigInteger.TEN)).limit(10).forEach(System.out::println);
	}

	@Test
	public void primitive() {
		IntStream.range(1,3).forEach(System.out::println);
		System.out.println("===========================");
		IntStream.range(0,3).mapToObj(i->"x").forEach(System.out::println);
		DoubleStream.of(1.1,2.2,3.3).forEach(System.out::println);
	}

	@Test
	public void filter() {
		orders.stream().filter(Objects::nonNull)
				.filter(order -> order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6)))
				.filter(order -> order.getTotalPrice()>40)
				.forEach(System.out::println);
	}

	@Test
	public void map() {
		LongAdder longAdder = new LongAdder();
		orders.stream().forEach(order -> order.getOrderItemList().forEach(orderItem -> longAdder.add(orderItem.getProductQuantity())));

		assertThat(longAdder.longValue(), is(orders.stream().mapToLong(
				order-> order.getOrderItemList().stream()
				.mapToLong(OrderItem::getProductQuantity)
				.sum()).sum()));

		System.out.println(IntStream.rangeClosed(1, 10).mapToObj(i -> new Product((long)i, "product" + i, i * 100.0))
		.collect(toList()));
	}

	@Test
	public void sorted() {
		orders.stream().filter(Objects::nonNull)
				.filter(order->order.getTotalPrice()>50)
				.sorted(Comparator.comparing(Order::getTotalPrice).reversed())
				.forEach(System.out::println);
	}

	/**
	 * 相当于 map+flat，通过 map 把每一个元素替换为一个流，然后展开这个流。
	 */
	@Test
	public void flatMap() {
		System.out.println(orders.stream().flatMap(order -> order.getOrderItemList().stream())
				.mapToDouble(item -> item.getProductQuantity() * item.getProductPrice())
				.sum());

		System.out.println(orders.stream().flatMapToDouble(
				order -> order.getOrderItemList()
						.stream().mapToDouble(item ->
								item.getProductQuantity() * item.getProductPrice()))
				.sum()
		);
	}

	// @Test
	// public void groupby() {
	// 	orders.stream().collect()
	// }
}
