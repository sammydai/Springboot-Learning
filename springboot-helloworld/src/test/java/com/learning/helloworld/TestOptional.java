package com.learning.helloworld;

import com.learning.helloworld.domain.City;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @Package: com.learning.helloworld
 * @Description: Test Optional
 * @Author: Sammy
 * @Date: 2020/12/8 09:09
 */

public class TestOptional {

	/**
	 * 和原来的null没区别，创建一个空的optional对象
	 * 原先返回return null； -》 return Optional.empty()
	 */
	@Test
	public void test1() {
		Optional<Object> empty = Optional.empty();
		System.out.println(empty);
	}

	@Test
	public void test2() {
		Optional<String> city = Optional.of("北京");
		System.out.println("city.get():  " + city.get());
		System.out.println("city:  " + city);
	}

	@Test
	public void test3() {
		Optional<String> of = Optional.of("null");//错误必须非空
		System.out.println(of);
	}

	@Test
	public void test4() {
		Optional<Object> op = Optional.ofNullable(null);
		Object ss = op.orElse("Shanghai");
		System.out.println(ss);
	}

	@Test
	public void test6() {
		Optional<String> of = Optional.ofNullable("北京");
		/*
		 * 		supllier<T> :	T get()
		 * 		如果容器中非空，就返回容器中的结果
		 * 		如果容器为空，就使用supplier这个供给型接口
		 */

		String string = of.orElseGet(()->"上海");
		System.out.println(string);
	}

	@Test
	public void test7() {
		Optional<String> of = Optional.ofNullable(null);
		/*
		 * 		supllier<T> :	T get()
		 * 		如果容器中非空，就返回容器中的结果
		 * 		如果容器为空，就使用supplier这个供给型接口
		 */

		String ss = of.orElseThrow(() -> new RuntimeException("用户信息错误"));
		System.out.println(ss);
	}

	public static String getName(City city) {
		return Optional.ofNullable(city)
				.map(cc -> cc.getCityName())
				.orElse("Unknown");
	}

	@Test
	public void test8() {
		System.out.println(getName(City.builder().cityName("London").cityCode(100).build()));
		assertEquals("Test Result for getName:","Unknown",getName(null));
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestOptional.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Test Cases finished successfully");
		}
	}
}
