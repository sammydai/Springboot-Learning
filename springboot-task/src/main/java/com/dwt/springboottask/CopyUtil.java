package com.dwt.springboottask;

import java.lang.reflect.Field;

/**
 * [一句话描述该类的功能]
 * 深度拷贝
 * 1、 基础的拷贝
 * 2、 a对象里有oobject，把这个对象的值也拷贝过来
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/15 18:53]
 */
public class CopyUtil {

	public static Object lock;

	public static Object copy(Object a) {
		synchronized (lock) {
			Field[] declaredFields = a.getClass().getDeclaredFields();
			Object b = a;
			for (Field declaredField : declaredFields) {
				declaredField.setAccessible(true);
				if (declaredField instanceof Object) {

					//b = declaredField;
				}
			}
			return b;
		}
	}
}
