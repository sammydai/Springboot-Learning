package com.learning.helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Package: com.learning.helloworld.domain
 * @Description: People
 * @Author: Sammy
 * @Date: 2020/12/6 13:05
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class People implements InitializingBean, DisposableBean {
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 城市信息
	 */
	private City city;

	public void myInit() {
		System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
	}

	public void myDestory() {
		System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
	}

	// 这是DiposibleBean接口方法
	@Override
	public void destroy() throws Exception {
		System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
	}
}
