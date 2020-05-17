package com.dwt.rabbitmqadvanced.scenes.topicExChange;

import org.checkerframework.checker.units.qual.C;
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
@RabbitListener(queues = "topic.message")
public class TopicMessageReceiver {

	@RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessageReceiver  : " +msg);
    }

}
