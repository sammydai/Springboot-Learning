package com.learning.socketio;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/4/19 14:18]
 */
@Slf4j
public class ClientExample {

	public static void main(String[] args) throws InterruptedException, URISyntaxException {
		String serverUrl = "http://localhost:9092";
		for (int i = 0; i < 10; i++) {
			log.info("id:{},start----->", i);
			SocketIOThread socketIOThread = new SocketIOThread(serverUrl, String.valueOf(i));
			socketIOThread.method();
			Thread.sleep(1000);
		}
	}


}

