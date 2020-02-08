package com.dwt.springbootexceptionhandler.controller;

import com.dwt.springbootexceptionhandler.constant.Status;
import com.dwt.springbootexceptionhandler.exception.JsonException;
import com.dwt.springbootexceptionhandler.exception.PageException;
import com.dwt.springbootexceptionhandler.model.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Package: com.dwt.springbootexceptionhandler.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 02:25
 */
@Controller
public class TestController {

	@GetMapping("/json")
	@ResponseBody
	public ApiResponse jsonException() {
		throw new JsonException(Status.UNKNOWN_ERROR);
	}

	@GetMapping("/page")
	public ModelAndView pageException() {
		throw new PageException(Status.UNKNOWN_ERROR);
	}
}
