package com.dwt;

import static org.junit.Assert.assertTrue;

import com.dwt.RabbitConsts.RabbitConsts;
import com.dwt.message.MessageStruct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbbitmqApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendDirect() {
        rabbitTemplate.convertAndSend(RabbitConsts.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message hhh sammy"));
    }
}
