package com.dwt.rabbitmqadvanced.scenes.fanoutExChange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.dwt.rabbitmqadvanced.scenes.fanoutExChange
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 13:48
 */
@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {

	@RabbitHandler
	public void process(String msg) {
		System.out.println("FanoutReceiverA  : " + msg);
	}
}
