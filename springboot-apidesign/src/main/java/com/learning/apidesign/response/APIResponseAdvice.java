package com.learning.apidesign.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.learning.apidesign.response
 * @Description: APIResponseAdvice
 * @Author: Sammy
 * @Date: 2020/12/21 21:47
 */
@Slf4j
@RestControllerAdvice
public class APIResponseAdvice implements ResponseBodyAdvice<Object> {

	@ExceptionHandler(APIException.class)
	public APIResponse handleApiException(HttpServletRequest request, APIException ex) {
		log.error("process url {} failed",request.getRequestURL().toString(),ex);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setSuccess(false);
		apiResponse.setCode(ex.getErrorCode());
		apiResponse.setMessage(ex.getErrorMessage());
		return apiResponse;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getParameterType() != APIResponse.class
				&& AnnotationUtils.findAnnotation(returnType.getMethod(), NoAPIResponse.class) == null
				&& AnnotationUtils.findAnnotation(returnType.getDeclaringClass(), NoAPIResponse.class) == null;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		APIResponse apiResponse = new APIResponse();
		apiResponse.setSuccess(true);
		apiResponse.setCode(2000);
		apiResponse.setMessage("OK");
		apiResponse.setData(body);
		return apiResponse;
	}
}
