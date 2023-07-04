package com.learning.kafka.controller;

import com.learning.kafka.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/7/3 14:59]
 */
@Component
@Slf4j
public class KafkaConsumer {

	@KafkaListener(topics = "quickstart-events", groupId = "group-id")
	public void listen(User user) {
		log.info("receive a message from kafka");
		System.out.println("Received Messasge in group - group-id: " + user);
	}

}
