package com.learning.helloworld.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @Package: com.learning.helloworld.filter
 * @Description: Filter Test
 * @Author: Sammy
 * @Date: 2020/12/6 16:06
 */
@Slf4j
public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("初始化过滤器");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
		String requestUri = request.getRequestURI();
		log.info("请求地址是："+requestUri);
		if (requestUri.contains("/addSession")
            || requestUri.contains("/removeSession")
            || requestUri.contains("/online")
            || requestUri.contains("/favicon.ico")) {
            filterChain.doFilter(servletRequest, wrapper);
        } else {
            wrapper.sendRedirect("/online");
        }
	}

	@Override
	public void destroy() {
 		log.info("销毁过滤器");
	}
}
