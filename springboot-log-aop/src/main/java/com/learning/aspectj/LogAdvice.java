package com.learning.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Package: com.learning.aspectj
 * @Description: Log Advice
 * @Author: Sammy
 * @Date: 2020/12/4 12:39
 */
@Aspect
@Component
@Slf4j
public class LogAdvice {

	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	private void logAdvicePointcut() {}

	@Before("logAdvicePointcut()")
	public void logAdvice() {
		log.info("get请求的advice触发了");
	}
}
