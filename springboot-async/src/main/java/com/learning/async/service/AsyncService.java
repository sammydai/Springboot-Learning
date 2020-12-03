package com.learning.async.service;

import com.learning.async.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Package: com.learning.async.service
 * @Description: GithubLookup Service
 * @Author: Sammy
 * @Date: 2020/11/30 16:44
 */
@Service
@Slf4j
public class AsyncService {

	private RestTemplate restTemplate;

	public AsyncService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async("asyncExecutor1")
	public Future<User> findUser2(String username) {
		log.info(Thread.currentThread().getName() + " looking up " + username);
		try {
			Thread.sleep(new Random().nextInt(2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<>(new User("sammydai", "xxxx"));
	}

	@Async
	public CompletableFuture<User> findUser(String username) throws InterruptedException {
		log.info(Thread.currentThread().getName() + " looking up " + username);
		String url = String.format("https://api.github.com/users/%s", username);
		User result = restTemplate.getForObject(url, User.class);
		Thread.sleep(1000L);
		return CompletableFuture.completedFuture(result);
	}
}
