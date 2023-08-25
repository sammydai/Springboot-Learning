package com.learning.utils;

import com.learning.domain.Cat;
import com.learning.domain.Dog;

import java.util.Date;

/**
 * 工具测试类
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/25 15:00]
 */
public class UtilsTest {

	public static void main(String[] args) {

		Cat cat = new Cat();
		cat.setName("tom");
		cat.setAge(5);
		cat.setColor("橘猫");
		cat.setBirth(new Date());
		cat.setPrice(5000);

		Cat copycat = CopyUtils.copy(cat);
		Dog copydog = CopyUtils.copy(cat, Dog.class);
		System.out.println("源对象cat " + cat);
		System.out.println("目标对象dog " + copydog);
		System.out.println("cat:" + cat.hashCode());
		System.out.println("copycat:" + copycat.hashCode());
		System.out.println("源对象hashcode:" + System.identityHashCode(cat));
		System.out.println("目标对象hashcode:" + System.identityHashCode(copydog));

	}
}
