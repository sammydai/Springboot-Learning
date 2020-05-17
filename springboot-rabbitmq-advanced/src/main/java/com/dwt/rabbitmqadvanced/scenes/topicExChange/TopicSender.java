package com.dwt.rabbitmqadvanced.scenes.topicExChange;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Package: com.dwt.rabbitmqadvanced.scenes.topicExChange
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 14:04
 */

@Component
public class TopicSender {

	@Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        System.out.println("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }

}
