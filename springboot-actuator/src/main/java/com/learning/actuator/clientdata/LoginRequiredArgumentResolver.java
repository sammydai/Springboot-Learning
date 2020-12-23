package com.learning.actuator.clientdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Package: com.learning.actuator.clientdata
 * @Description: LoginRequiredArgumentResolver
 * @Author: Sammy
 * @Date: 2020/12/23 10:20
 */
@Slf4j
public class LoginRequiredArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		//匹配参数上具有@LoginRequired注解的参数
		return methodParameter.hasParameterAnnotation(LoginRequired.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		LoginRequired loginRequired = methodParameter.getParameterAnnotation(LoginRequired.class);
		Object object = nativeWebRequest.getAttribute(loginRequired.sessionKey(), NativeWebRequest.SCOPE_SESSION);
		if (object==null) {
			log.error("接口{}非法调用", methodParameter.getMethod().toString());
			throw new RuntimeException("请先登陆！");
		}
		return object;
	}
}
