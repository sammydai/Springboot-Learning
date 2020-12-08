package com.learning.helloworld;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.learning.helloworld
 * @Description: Bean Lifecycle Tests
 * @Author: Sammy
 * @Date: 2020/12/7 11:04
 */
@Slf4j
public class HelloBeanTests {

	private List<String> wrongMethod(FooService fooService, Integer i, String s, String t) {
		log.info("result {} {} {} {}", i + 1, s.equals("OK"), s.equals(t),
				new ConcurrentHashMap<String, String>().put(null, null));
		if (fooService.getBarService().bar().equals("OK"))
			log.info("OK");
		return null;
	}

	@GetMapping("wrong")
	public int wrong(@RequestParam(value = "test", defaultValue = "1111") String test) {
		return wrongMethod(test.charAt(0) == '1' ? null : new FooService(),
				test.charAt(1) == '1' ? null : 1,
				test.charAt(2) == '1' ? null : "OK",
				test.charAt(3) == '1' ? null : "OK").size();
	}

	class FooService {
		@Getter
		private BarService barService;

	}

	class BarService {
		String bar() {
			return "OK";
		}
	}
}
