package com.learning.helloworld.filter;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

/**
 * @Package: com.learning.helloworld.filter
 * @Description: MyHttpRequestListener
 * @Author: Sammy
 * @Date: 2020/12/6 19:05
 */

public class MyHttpRequestListener implements ServletRequestListener {
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("request 监听器被销毁");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String requestURI = req.getRequestURI();
        System.out.println(requestURI+"--"+"被调用");
	}
}
