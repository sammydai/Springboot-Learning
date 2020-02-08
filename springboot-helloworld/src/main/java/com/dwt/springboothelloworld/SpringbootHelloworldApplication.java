package com.dwt.springboothelloworld;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class SpringbootHelloworldApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootHelloworldApplication.class, args);
	}

	@GetMapping(value = "/hello")
	public String getHello(@RequestParam(name = "who",required = false) String who) {
		if (StrUtil.isBlank(who)) {
			who = "World";
		}
		return StrUtil.format("Hello, {}!", who);
	}

}
