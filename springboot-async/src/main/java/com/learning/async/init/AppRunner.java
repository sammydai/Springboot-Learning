package com.learning.async.init;

import com.learning.async.domain.User;
import com.learning.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Package: com.learning.async.init
 * @Description: App Runner
 * @Author: Sammy
 * @Date: 2020/11/30 15:38
 */

@Component
@Slf4j
public class AppRunner implements CommandLineRunner {

	@Autowired
	AsyncService asyncService;

	@Override
	public void run(String... args) throws Exception {
		log.info("============================>");
		log.info("start app command line.....!");
		Future<User> futurePage1 = asyncService.findUser("msssm");
		Future<User> futurePage2 = asyncService.findUser("sm2");
		Future<User> futurePage3 = asyncService.findUser("sm3");
		log.info("-->" + futurePage1.get());
		log.info("-->" + futurePage2.get());
		log.info("-->" + futurePage3.get());

	}
}
