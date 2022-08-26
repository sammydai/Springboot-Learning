package com.learning.helloworld.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Package: com.learning.helloworld.filter
 * @Description: Interceptor2
 * @Author: Sammy
 * @Date: 2022/6/23 16:39
 */
@Slf4j
@Component
public class MyInterceptor2 implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("pre【MyInterceptor2】preHandle 被调用成功",request.getRequestURI());
		request.setAttribute("preHandleTime",System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info(">>>>>>>>>>>>开始postHandle");
		HttpSession session = request.getSession();
		log.info(">>>>>>>>>>>>");
		if (!request.getRequestURI().contains("/online")) {
			String sessionName = (String) session.getAttribute("name");
			if ("haixiang".equals(sessionName)) {
                log.info("post【MyInterceptor2】当前浏览器存在 session:{}",sessionName);
            }
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		long duration = System.currentTimeMillis() - (Long) request.getAttribute("preHandleTime");
        log.info("after【MyInterceptor2】[{}]调用耗时:{}ms",request.getRequestURI(), duration);

	}
}
