package com.learning.helloworld.reflection;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @Package: com.learning.helloworld.reflection
 * @Description: ReflectionIssueApplication
 * @Author: Sammy
 * @Date: 2020/12/16 15:06
 */
@Slf4j
public class ReflectionIssueApplication {
	private void age(int age) {
		log.info("int age = {}", age);
	}

	private void age(Integer age) {
		log.info("Integer age = {}", age);
	}

	/**
	 * 根据入参来判断使用哪个重载方法
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ReflectionIssueApplication rfa = new ReflectionIssueApplication();
		// rfa.wrong();
		rfa.right();
	}

	public void wrong() throws Exception {
		getClass().getDeclaredMethod("age", Integer.TYPE).invoke(this, Integer.valueOf("54"));
	}

	/**
	 * 反射调用方法，是以反射获取方法时传入的方法名称和参数类型来确定调用方法的
	 * @throws Exception
	 */
	public void right() throws Exception{
		getClass().getDeclaredMethod("age", Integer.class).invoke(this, Integer.valueOf("54"));
		getClass().getDeclaredMethod("age", Integer.class).invoke(this, 90);
	}
}
