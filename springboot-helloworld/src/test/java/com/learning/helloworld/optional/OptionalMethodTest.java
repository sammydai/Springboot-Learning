package com.learning.helloworld.optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.learning.helloworld.domain.City;
import com.learning.helloworld.domain.People;
import com.learning.helloworld.nullvalue.UserDto;
import com.learning.helloworld.nullvalue.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;
import static com.learning.helloworld.optional.OptionalMethod.getName;
import static org.junit.Assert.*;

/**
 *
 *@Package: com.learning.helloworld.optional
 *@Description: Optional Method Test
 *@Author: Sammy
 *@Date: 2020/12/8 17:04
 *
 **/
@Slf4j
public class OptionalMethodTest {

	// @Test(expected = IllegalArgumentException.class)
	@Test
	public void getCityNameRight() {
		City city = new City("london", 200);
		People pp = new People("kfdeveloper", 25, city);
		assertEquals("london",OptionalMethod.getCityNameRight(pp));
	}

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

		String string = of.orElseGet(() -> "上海");
		System.out.println(string);
	}

	@Test(expected = RuntimeException.class)
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

	@Test
	public void test8() {
		System.out.println(getName(City.builder().cityName("London").cityCode(100).build()));
		assertEquals("Test Result for getName:", "Unknown", getName(null));
	}

	@Test
	public void test9() {
		UserDto userDto = new UserDto();
		Optional<String> name = userDto.getName();
		System.out.println(name);
	}

	@Test
	public void test10() {
		UserDto userDto = new UserDto();
		userDto.setName(null);
		String ss = userDto.getName().orElse("sssother");
		System.out.println(ss);
	}

	@Test
	public void test11() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        UserDto result = objectMapper.readValue("{\"id\":\"1\", \"age\":30, \"name\":null}",UserDto.class);
        log.info("field name with null value dto:{} name:{}", result, result.getName().orElse("N/A"));
        //field name with null value dto:UserDto(id=1, name=Optional.empty, age=Optional[30]) name:N/A
        log.info("missing field name dto:{}",objectMapper.readValue("{\"id\":\"1\", \"age\":30}",UserDto.class));
        // missing field name dto:UserDto(id=1, name=null, age=Optional[30])
	}
}