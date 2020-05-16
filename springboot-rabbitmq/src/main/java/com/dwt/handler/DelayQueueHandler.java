package com.dwt.handler;

import cn.hutool.json.JSONUtil;
import com.dwt.RabbitConsts.RabbitConsts;
import com.dwt.message.MessageStruct;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Package: com.dwt.handler
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 02:31
 */

@Slf4j
@Component
@RabbitListener(queues = RabbitConsts.DELAY_QUEUE)
public class DelayQueueHandler {

	@RabbitHandler
	public void directHandlerManualAck(MessageStruct messageStruct, Message message, Channel channel) {
		final long deliveryTag = message.getMessageProperties().getDeliveryTag();
		try {
			log.info("延迟队列，手动ACK，接收消息：{}", JSONUtil.toJsonStr(messageStruct));
			// 通知 MQ 消息已被成功消费,可以ACK了
			channel.basicAck(deliveryTag, false);
		} catch (IOException e) {
			try {
				// 处理失败,重新压入MQ
				channel.basicRecover();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
