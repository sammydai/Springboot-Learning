package com.learning.helloworld.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.learning.helloworld.filter
 * @Description: My HttpSession Listener
 * @Author: Sammy
 * @Date: 2020/12/6 16:27
 */
@Slf4j
public class MyHttpSessionListener implements HttpSessionListener {

	public static AtomicInteger userCount = new AtomicInteger(0);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		userCount.getAndIncrement();
		se.getSession().getServletContext().setAttribute("sessionCount",userCount.get());
		log.info("【在线人数】人数增加为:{}",userCount.get());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		userCount.getAndDecrement();
		se.getSession().getServletContext().setAttribute("sessionCount", userCount.get());
        log.info("【在线人数】人数减少为:{}",userCount.get());
	}
}
