package com.learning.helloworld.config;

import cn.hutool.core.util.PageUtil;
import com.learning.helloworld.filter.MyFilter;
import com.learning.helloworld.filter.MyHttpSessionListener;
import com.learning.helloworld.filter.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.EventListener;

/**
 * @Package: com.learning.helloworld.config
 * @Description: WebConfig
 * @Author: Sammy
 * @Date: 2020/12/6 18:03
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private MyInterceptor myInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new MyFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
		ServletListenerRegistrationBean<EventListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
		listenerRegistrationBean.setListener(new MyHttpSessionListener());
		return listenerRegistrationBean;
	}

}
