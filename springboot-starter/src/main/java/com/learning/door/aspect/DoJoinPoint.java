package com.learning.door.aspect;

import com.alibaba.fastjson.JSON;
import com.learning.door.annotation.DoDoor;
import com.learning.door.service.StarterService;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/15 15:14]
 */
@Component
@Aspect
public class DoJoinPoint {

	private final Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);

	@Autowired
	private StarterService starterService;

	@Pointcut("@annotation(com.learning.door.annotation.DoDoor)")
	public void aopPoint() {
	}

	@Around("aopPoint()")
	public Object doRouter(ProceedingJoinPoint jp) throws Throwable {
		Method method = getMethod(jp);
		DoDoor door = method.getAnnotation(DoDoor.class);
		//获取字段值
		String keyValue = getFiledValue(door.key(), jp.getArgs());
		logger.info("itstack door handler method：{} value：{}", method.getName(), keyValue);
		if (null == keyValue || "".equals(keyValue)) return jp.proceed();
		//配置内容
		String[] split = starterService.split(",");
		for (String str : split) {
			if (keyValue.equals(str)) {
				return jp.proceed();
			}
		}
		return returnObject(door, method);

	}

	private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
		Signature sig = jp.getSignature();
		MethodSignature methodSignature = (MethodSignature) sig;
		return getClass(jp).getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
	}

	private Class<? extends Object> getClass(JoinPoint jp) throws NoSuchMethodException {
		return jp.getTarget().getClass();
	}

	//获取属性值
	private String getFiledValue(String filed, Object[] args) {
		String filedValue = null;
		for (Object arg : args) {
			try {
				if (null == filedValue || "".equals(filedValue)) {
					filedValue = BeanUtils.getProperty(arg, filed);
				} else {
					break;
				}
			} catch (Exception e) {
				if (args.length == 1) {
					return args[0].toString();
				}
			}
		}
		return filedValue;
	}

	//返回对象
	private Object returnObject(DoDoor doGate, Method method) throws IllegalAccessException, InstantiationException {
		Class<?> returnType = method.getReturnType();
		String returnJson = doGate.returnJson();
		if ("".equals(returnJson)) {
			return returnType.newInstance();
		}
		return JSON.parseObject(returnJson, returnType);
	}
}
