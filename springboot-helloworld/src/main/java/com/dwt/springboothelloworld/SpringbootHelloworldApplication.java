package com.dwt.springboothelloworld;

import cn.hutool.core.util.StrUtil;
import com.dwt.springboothelloworld.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;


@RestController
@SpringBootApplication
public class SpringbootHelloworldApplication {

	@Autowired
	private ServerConfig serverConfig;

	public static void main(String[] args) {

		SpringApplication.run(SpringbootHelloworldApplication.class, args);
	}

	@GetMapping(value = "/httpserver/hello")
	public String getHello(@RequestParam(name = "who",required = false) String who) {
		if (StrUtil.isBlank(who)) {
			who = "World First this is localhost !!! "+serverConfig.getUrl();
		}
		return StrUtil.format("Hello, {}!", who);
	}

}
