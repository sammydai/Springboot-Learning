package com.learning.zkprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *@Package: com.learning.zkprovider.controller;
 *@Description: ZKProviderController
 *@Author: Sammy
 *@Date: 2020/12/16 17:07
 *
 **/
@RestController
public class ZKProviderController {
    private AtomicInteger count = new AtomicInteger();
    private AtomicInteger sleepCount = new AtomicInteger();

    @GetMapping("/zk/provider")
    public String HelloWorld(@RequestParam("name") String name) {
		int newCount = count.incrementAndGet();
		return "【This is ZK Provider Msg】: Hello World!" + name +"ribbon count: "+newCount;
    }

    @GetMapping("/zk/provider/sleep")
    public String HelloWorldSleep(@RequestParam("name") String name) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		int newCount = sleepCount.incrementAndGet();
        return "ribbon sleep count" + newCount + ": " + ThreadLocalRandom.current().nextInt(1000)+name;

	}

}

