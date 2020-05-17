package com.dwt.rabbitmqadvanced.scenes.topicExChange;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.dwt.rabbitmqadvanced.scenes.topicExChange
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 14:02
 */

@Component
@RabbitListener(queues = "topic.messages")
public class TopicMessagesReceiver {

	@RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessagesssReceiver  : " +msg);
    }

}
