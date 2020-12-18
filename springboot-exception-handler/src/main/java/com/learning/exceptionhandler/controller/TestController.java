package com.learning.exceptionhandler.controller;

import com.learning.exceptionhandler.constant.APIResponse;
import com.learning.exceptionhandler.constant.Status;
import com.learning.exceptionhandler.exception.BusinessException;
import com.learning.exceptionhandler.exception.JsonException;
import com.learning.exceptionhandler.exception.PageException;
import com.learning.exceptionhandler.model.ApiResponse;
import com.learning.exceptionhandler.service.ReadFileService;
import com.learning.exceptionhandler.service.TestResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Package: com.dwt.springbootexceptionhandler.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 02:25
 */
@Controller
@Slf4j
public class TestController {

	@Autowired
	private ReadFileService readFileService;

	@Autowired
	private TestResource testResource;

	@GetMapping("/json")
	@ResponseBody
	public ApiResponse jsonException() {
		throw new JsonException(Status.UNKNOWN_ERROR);
	}

	@GetMapping("/page")
	public ModelAndView pageException() {
		throw new PageException(Status.UNKNOWN_ERROR);
	}

	@GetMapping("/business")
	public APIResponse businessException() {
		log.info("=======>invoke business exception");
		throw new BusinessException("business exception cannot find user", 2010);
	}

	@GetMapping("/exc")
	public APIResponse divideException() {
		log.info("======>invoke 1/0");
		int a = 1 / 0;
		return null;
	}

	@GetMapping("/wrong1")
	public void wrong1() {
		try {
			readFileService.readFile();
		} catch (IOException e) {
			throw new RuntimeException("系统繁忙请稍后再试");
		}
	}

	@GetMapping("/wrong2")
	public void wrong2() {
		try {
			readFileService.readFile();
		} catch (IOException e) {
			log.error("文件读取错误,{}", e.getMessage());
			throw new RuntimeException("系统繁忙请稍后再试");
		}
	}

	@GetMapping("/right1")
	public void right1() {
		try {
			readFileService.readFile();
		} catch (IOException e) {
			log.error("文件读取错误,{}", e);
			throw new RuntimeException("系统繁忙请稍后再试");
		}
	}

	@GetMapping("/right2")
	public void right2() {
		try {
			readFileService.readFile();
		} catch (IOException e) {
			throw new RuntimeException("系统繁忙请稍后再试", e);
		}
	}

	@GetMapping("wrongorder")
	public void wrong() {
		try {
			readFileService.createOrderWrong();
		} catch (Exception ex) {
			log.error("createOrder got error", ex);
		}
		try {
			readFileService.cancelOrderWrong();
		} catch (Exception ex) {
			log.error("cancelOrder got error", ex);
		}
	}


	@GetMapping("wrongfinally")
	public void wrongfinally() {
		try {
			log.info("try");
			//异常丢失
			throw new RuntimeException("try");
		} finally {
			log.info("finally");
			throw new RuntimeException("finally");
		}
	}


	@GetMapping("rightfinally")
	public void rightfinally() {
		try {
			log.info("try");
			throw new RuntimeException("try");
		} finally {
			log.info("finally");
			try {
				throw new RuntimeException("finally");
			} catch (Exception ex) {
				log.error("finally", ex);
			}
		}
	}


	@GetMapping("rightfinally2")
	public void rightfinally2() throws Exception {
		Exception e = null;
		try {
			log.info("try");
			throw new RuntimeException("try");
		} catch (Exception ex) {
			e = ex;
		} finally {
			log.info("finally");
			try {
				throw new RuntimeException("finally");
			} catch (Exception ex) {
				if (e != null) {
					e.addSuppressed(ex);
				} else {
					e = ex;
				}
			}
		}
		throw e;
	}

	@GetMapping("useresourcewrong")
	public void useresourcewrong() throws Exception {
		try {
			testResource.read();
		} finally {
			testResource.close();
		}
	}

	@GetMapping("useresourceright")
	public void useresourceright() throws Exception {
		try (TestResource ts = new TestResource()) {
			ts.read();
		}
	}
}
