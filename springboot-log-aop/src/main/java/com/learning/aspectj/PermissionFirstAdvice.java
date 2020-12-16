package com.learning.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * @Package: com.learning.aspectj
 * @Description: PermissionFirstAdvice
 * @Author: Sammy
 * @Date: 2020/12/4 12:50
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class PermissionFirstAdvice {

	@Pointcut("@annotation(com.learning.annotation.PermissionAnnotation)")
	private void permissionCheck() {
	}

	@Around("permissionCheck()")
	public Object permissionCheckFirst(ProceedingJoinPoint joinpoint) throws Throwable {
		log.info("===================第一个切面===================：" + System.currentTimeMillis());
		Object[] objects = joinpoint.getArgs();
		Long id = ((JSONObject) objects[0]).getLong("id");
        String name = ((JSONObject) objects[0]).getString("name");
        log.info("id1->>>>>>>>>>>>>>>>>>>>>>" + id);
        log.info("name1->>>>>>>>>>>>>>>>>>>>>>" + name);
        // id小于0则抛出非法id的异常
        if (id < 0) {
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
		return joinpoint.proceed();
	}

}
