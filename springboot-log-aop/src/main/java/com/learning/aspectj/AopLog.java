package com.learning.aspectj;

import cn.hutool.json.JSONUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 *
 *
 *@description:
 *@author: Sammy
 *@time: 2020/2/3 02:07
 *
 */
@Slf4j
@Component
@Aspect
public class AopLog {
    private static final String START_TIME = "request-start";

    @Pointcut("execution(public * com.learning.controller.TestController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        log.info("【请求 URL】：{}",request.getRequestURL());
        log.info("【请求 IP】：{}",request.getRemoteAddr());
        log.info("【请求类名】：{}，【请求方法名】：{}",point.getSignature().getDeclaringTypeName(),point.getSignature().getName() );

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("【请求参数】：{}，", JSONUtil.toJsonStr(parameterMap));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        log.info("【返回值】：{}", JSONUtil.toJsonStr(result));
        return result;
    }

    @After("log()")
    public void doAfter() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();

        log.info("【请求耗时】：{}毫秒", end - start);
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        log.info("【浏览器类型】：{}，【操作系统】：{}，【原始User-Agent】：{}",userAgent.getBrowser().toString(),userAgent.getOperatingSystem().toString(),header );
    }
}
