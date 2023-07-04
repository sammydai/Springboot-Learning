package com.learning.kafka.controller;

import com.learning.kafka.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/30 10:27]
 */
@RestController
@Slf4j
public class ProducerController {

	private static final String TOPIC = "quickstart-events";

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	@RequestMapping("/test")
	public String sendMessage(@RequestBody User user) {
		kafkaTemplate.send(TOPIC, user);
		log.info("send a message to kafka");
		return "Published Successfully";
	}
}
