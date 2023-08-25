package com.learning.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/25 17:32]
 */
public class CopyUtils {
	public static <T> T copy(T source) {
		if (Objects.isNull(source)) {
			return null;
		}
		Class<?> aClass = source.getClass();
		List<Field> fields = getFields(aClass);
		return newInstance(source, aClass, fields);
	}

	/**
	 * 单个深度拷贝
	 *
	 * @param source 源实例化对象
	 * @param target 目标对象类
	 * @return {@link T}
	 */
	public static <T> T copy(Object source, Class<T> target) {
		if (Objects.isNull(source) || Objects.isNull(target)) {
			return null;
		}
		List<Field> sourceFields = getFields(source.getClass());
		List<Field> targetFields = getFields(target);
		T t = null;
		try {
			t = newInstance(source, target, sourceFields, targetFields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	private static <T> T newInstance(Object source, Class<T> target, List<Field> sourceFields, List<Field> targetFields) throws Exception {
		T t = target.newInstance();
		if (targetFields.isEmpty()) {
			return t;
		}
		for (Field field : sourceFields) {
			field.setAccessible(true);
			Object value = field.get(source);
			Field sameField = getSameField(field, targetFields);
			if (Objects.nonNull(sameField)) {
				sameField.setAccessible(true);
				sameField.set(t, value);
			}
		}
		return t;
	}

	/**
	 * 获取目标对象中同源对象属性相同的属性（字段名称，字段类型一致则判定为相同）
	 *
	 * @param field        场
	 * @param targetFields 目标字段
	 * @return {@link Field} 目标对象相同的属性
	 */
	private static Field getSameField(Field field, List<Field> targetFields) {
		String fieldName = field.getName();
		String fieldTypeName = field.getType().getName();
		for (Field targetField : targetFields) {
			if (fieldName.equals(targetField.getName()) && fieldTypeName.equals(targetField.getType().getName())) {
				return targetField;
			}
		}
		return null;
	}

	/**
	 * 实例化同源对戏那个
	 *
	 * @param source 源对象
	 * @param aClass 源对象类名
	 * @param fields 源对象属性集合
	 * @return {@link T}  同源新对象
	 */
	private static <T> T newInstance(T source, Class<?> aClass, List<Field> fields) {
		T t = null;
		try {
			t = (T) aClass.newInstance();
			for (Field field : fields) {
				field.setAccessible(true);
				field.set(t, field.get(source));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 获取字段,需要包括
	 *
	 * @param aClass 一个类
	 * @return {@link List}<{@link Field}>
	 */
	private static List<Field> getFields(Class<?> aClass) {
		List<Field> result = new ArrayList<>();
		// Field[] fields = aClass.getFields();
		//没有继承父类的
		Field[] fields = aClass.getDeclaredFields();
		for (Field field : fields) {
			result.add(field);
		}
		return getSuperClassFields(aClass, result);
	}

	/**
	 * 递归获取父类属性
	 *
	 * @param o      一个类
	 * @param result 结果
	 * @return {@link List}<{@link Field}>
	 */
	private static List<Field> getSuperClassFields(Class<?> o, List<Field> result) {
		Class<?> superclass = o.getSuperclass();
		if (Objects.isNull(superclass) || Object.class.getName().equals(superclass.getName())) {
			return result;
		}
		Field[] fields = superclass.getDeclaredFields();
		if (fields.length == 0) {
			return result;
		}
		result.addAll(Arrays.asList(fields));
		// return result;
		// 递归方法
		return getSuperClassFields(superclass, result);
	}

}
