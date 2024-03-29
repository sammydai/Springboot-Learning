package com.learning.actuator.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.learning.actuator.health
 * @Description: UserService Controller
 * @Author: Sammy
 * @Date: 2020/12/22 16:47
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserServiceController {

	@GetMapping
	public User getUser(@RequestParam("userId") long id) {
		if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
			return new User(id, "name" + id);
		} else {
			// throw new RuntimeException("error");
			return new User(id, "name" + id);
		}
	}

	@GetMapping("slowTask")
    public void slowTask() {
		log.info("==============>slowTask");
        ThreadPoolProvider.getDemoThreadPool().execute(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
            }
        });
    }
}
