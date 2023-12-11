package com.learning.kafka.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/10/18 16:19]
 */
public class ThreadPoolInterceptor implements HandlerInterceptor {
	public static Map<String, ThreadPoolExecutor> poolcontainer = new ConcurrentHashMap<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			ThreadPool threadPoolAnnotation = handlerMethod.getMethodAnnotation(ThreadPool.class);
			if (threadPoolAnnotation != null) {
				String threadPoolName = threadPoolAnnotation.value();
				if (poolcontainer.get(threadPoolName) == null) {
					ThreadPoolExecutor threadPool = getThreadPoolByName(threadPoolName);
					poolcontainer.putIfAbsent(threadPoolName, threadPool);
				}
			}
		}
		return true;
	}

	private ThreadPoolExecutor getThreadPoolByName(String threadPoolName) {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5, new NameThreadFactory(threadPoolName));
		return pool;
	}
}
