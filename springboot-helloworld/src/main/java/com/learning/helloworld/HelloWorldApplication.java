package com.learning.helloworld;

import cn.hutool.core.util.StrUtil;
import com.learning.helloworld.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class HelloWorldApplication {

	@Autowired
	private ServerConfig serverConfig;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HelloWorldApplication.class, args);
		// SpringApplication sa = new SpringApplication(HelloWorldApplication.class);
		// sa.run(args);
		// People bean = ApplicationContextUtils.getBean(People.class);
	}

	@GetMapping(value = "/httpserver/hello")
	public String getHello(@RequestParam(name = "who",required = false) String who) {
		if (StrUtil.isBlank(who)) {
			who = "World First this is localhost !!! "+serverConfig.getUrl();
		}
		return StrUtil.format("Hello, {}!", who);
	}

}
