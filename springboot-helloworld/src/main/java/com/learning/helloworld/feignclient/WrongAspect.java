// package com.learning.helloworld.feignclient;
//
// import lombok.extern.slf4j.Slf4j;
// import org.aopalliance.intercept.Joinpoint;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.springframework.stereotype.Component;
//
// /**
//  * @Package: com.learning.helloworld.feignclient
//  * @Description: WrongAspect
//  * @Author: Sammy
//  * @Date: 2020/12/16 16:28
//  */
// @Aspect
// @Slf4j
// @Component
// public class WrongAspect {
//
// 	// @Before("within(feign.Client+)")
// 	public void before(Joinpoint pjp) {
// 		log.info("within(feign.Client+) pjp {}, args:{}", pjp, pjp.);
// 	}
// }
