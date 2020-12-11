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
 * @Description: MyInterceptor
 * @Author: Sammy
 * @Date: 2020/12/6 16:13
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("【MyInterceptor】调用了:{}", request.getRequestURI());
		request.setAttribute("requestTime",System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// HttpSession session = request.getSession();
		// if (!request.getRequestURI().contains("/online")) {
		// 	String sessionName = (String) session.getAttribute("name");
		// 	if ("haixiang".equals(sessionName)) {
         //        log.info("【MyInterceptor】当前浏览器存在 session:{}",sessionName);
         //    }
		// }
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		long duration = System.currentTimeMillis() - (Long) request.getAttribute("requestTime");
        log.info("【MyInterceptor】[{}]调用耗时:{}ms",request.getRequestURI(), duration);
	}
}
