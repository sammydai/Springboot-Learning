package com.dwt.springbootzookeeperkey.aspectj;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dwt.springbootzookeeperkey.anotation.LockKeyParam;
import com.dwt.springbootzookeeperkey.anotation.ZooLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Package: com.dwt.springbootzookeeperkey.aspectj
 * @Description:
 * @Author: Sammy
 * @Date: 2020/4/5 00:00
 */

@Aspect
@Component
@Slf4j
public class ZooLockAspect {

	private final CuratorFramework zkClient;

	private static final String KEY_PREFIX = "DISTRIBUTED_LOCK_";

	private static final String KEY_SEPARATOR = "/";

	@Autowired
	public ZooLockAspect(CuratorFramework zkClient) {
		this.zkClient = zkClient;
	}

	@Pointcut(value = "@annotation(com.dwt.springbootzookeeperkey.anotation.ZooLock)")
	public void doLock() {
	}

	@Around("doLock()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		Object[] args = point.getArgs();
		ZooLock zooLock = method.getAnnotation(ZooLock.class);
		if (StrUtil.isBlank(zooLock.key())) {
			throw new RuntimeException("分布式锁键不能为空");
		}
		String lockKey = buildLockKey(zooLock, method, args);
		InterProcessMutex lock = new InterProcessMutex(zkClient, lockKey);

		try {
			if (lock.acquire(zooLock.timeout(), zooLock.timeUnit())) {
				return point.proceed();
			} else {
				throw new RuntimeException("请勿重复提交");
			}
		}finally {
			lock.release();
		}
	}

	private String buildLockKey(ZooLock zooLock,Method method,Object[] args) throws Exception {
		StringBuilder key = new StringBuilder(KEY_SEPARATOR + KEY_PREFIX + zooLock.key());

		// 迭代全部参数的注解，根据使用LockKeyParam的注解的参数所在的下标，来获取args中对应下标的参数值拼接到前半部分key上
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parameterAnnotations.length; i++) {
			for (Annotation annotation : parameterAnnotations[i]) {
				if (!annotation.annotationType().isInstance(LockKeyParam.class)) {
					continue;
				}
				String[] fields = ((LockKeyParam) annotation).fields();
				if (ArrayUtil.isEmpty(fields)) {
					if (ObjectUtil.isNull(fields[i])) {
						throw new RuntimeException("动态参数不能为null");
					}
					key.append(KEY_SEPARATOR).append(args[i]);
				} else {
					// @LockKeyParam的fields值不为null，所以当前参数应该是对象类型
					for (String field : fields) {
						Class<?> clazz = args[i].getClass();
						Field declaredField = clazz.getDeclaredField(field);
						declaredField.setAccessible(true);
						Object value = declaredField.get(clazz);
						key.append(KEY_SEPARATOR).append(value);
					}
				}
			}
		}
		return key.toString();
	}

}
