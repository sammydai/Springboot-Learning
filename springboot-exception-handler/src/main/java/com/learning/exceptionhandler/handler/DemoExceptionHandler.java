package com.learning.exceptionhandler.handler;

import com.learning.exceptionhandler.exception.JsonException;
import com.learning.exceptionhandler.exception.PageException;
import com.learning.exceptionhandler.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Package: com.dwt.springbootexceptionhandler.handler
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 02:19
 */
@Slf4j
@ControllerAdvice
public class DemoExceptionHandler {
	private static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = JsonException.class)
	@ResponseBody
	public ApiResponse jsonErrorHandler(JsonException exception) {
		log.error("【JsonException】:{}", exception.getMessage());
		return ApiResponse.ofException(exception);
	}

	@ExceptionHandler(value = PageException.class)
	public ModelAndView pageErrorHandler(PageException exception) {
		log.error("【DemoPageException】:{}", exception.getMessage());
		ModelAndView view = new ModelAndView();
		view.addObject("message", exception.getMessage());
		view.setViewName(DEFAULT_ERROR_VIEW);
		return view;
	}
}
