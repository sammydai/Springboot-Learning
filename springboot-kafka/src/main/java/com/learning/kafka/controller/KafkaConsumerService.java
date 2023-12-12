package com.learning.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


/**
 * KafkaConsumerService
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/7/3 14:59]
 */
@Component
@Slf4j
public class KafkaConsumerService {

	// @KafkaHandler
	@KafkaListener(topics = "quickstart-events", groupId = "group-id")
	public void listen(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) {
		log.info("receive a message from kafka");
		log.info("topic {}", record.topic());
		log.info("key {}", record.key());
		log.info("value {}", record.value());
		log.info("headers {}", record.headers());
		log.info("partition {}", record.partition());
		log.info("offset {}", record.offset());
		System.out.println("Received Messasge in group - group-id");
		//ack 确认
		acknowledgment.acknowledge();
	}


}
