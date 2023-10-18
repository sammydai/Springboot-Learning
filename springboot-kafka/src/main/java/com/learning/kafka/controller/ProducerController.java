package com.learning.kafka.controller;

import com.learning.kafka.domain.User;
import com.learning.kafka.interceptor.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ProducerController
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/30 10:27]
 */
@RestController
@Slf4j
public class ProducerController {

	/**
	 * spring 提供的线程池
	 */
	@Autowired
	private ThreadPoolTaskExecutor poolTaskExecutor;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private HttpServletRequest request;

	private static final String TOPIC = "quickstart-events";

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	@RequestMapping("/test")
	@ThreadPool("threadPoolB")
	public String sendMessage(@RequestBody User user) {
		System.out.println("==========>" + request.getRequestURL());
		log.info("threadpoolname: {}", request.getAttribute("threadPoolName"));
		ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(TOPIC, user);
		future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
			}

			@Override
			public void onSuccess(SendResult<String, User> result) {
				System.out.println(result.getProducerRecord());
				System.out.println(result.getRecordMetadata());
				System.out.println("thread name: " + Thread.currentThread().getName());
			}
		});
		log.info("send a message to kafka");
		return "Published Successfully";
	}

	@RequestMapping("/abc")
	@ThreadPool("threadPoolA")
	public void sendMessageSnd(@RequestBody User user) {
		poolTaskExecutor.execute(() -> {
			// log.info("threadpoolname: {}",request.getAttribute("threadPoolName"));
			// log.info("request path{}",request.getRequestURL());
			System.out.println("thread name: " + Thread.currentThread().getName());
		});
	}

}
