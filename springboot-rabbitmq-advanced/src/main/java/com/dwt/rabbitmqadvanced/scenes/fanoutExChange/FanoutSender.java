package com.dwt.rabbitmqadvanced.scenes.fanoutExChange;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Package: com.dwt.rabbitmqadvanced.scenes.fanoutExChange
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 13:49
 */
@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String msgString="fanoutSender :hello i am sammy";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend("fanoutExchange","abcd.ee", msgString);
	}
}
