package com.dwt.springboothello;

import com.sun.deploy.resources.Deployment_pt_BR;
import com.sun.tools.javac.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.oops.BranchData;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@SpringBootApplication
public class SpringbootHelloApplication {

	public final static Logger LOGGER = LoggerFactory.getLogger(SpringbootHelloApplication.class);

	public final static String[] VISIT = new String[]{"浏览界面","评论商品","加入收藏","加入评论","提交订单","使用优惠券","搜索","查看订单","加入购物车","账号密码错误"};

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(SpringbootHelloApplication.class, args);

		while (true) {
			Thread.sleep(RandomUtil.randomNum(5)*1000);
			String userId = RandomUtil.generateMixString(4);
			String visit = VISIT[RandomUtil.randomNum(VISIT.length)];
			String date = RandomUtil.formatDate();
			String result = "DAU|" + userId + "|" + visit + "|" + date;
			LOGGER.info(result);
		}
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello-msg";
	}

}
