package com.dwt.rabbitmqadvanced.controller;

import com.dwt.rabbitmqadvanced.scenes.topicExChange.TopicSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.dwt.rabbitmqadvanced.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 13:57
 */
@RestController
@RequestMapping("/rabbit5")
@Api(value = "topic exchange类型rabbitmq",description = "")
public class TopicExchangeController {

	@Autowired
    private TopicSender topicSender;

    @RequestMapping(value = "/topic",method = RequestMethod.GET)
    public void topicTest() {
        topicSender.send();
    }
}
