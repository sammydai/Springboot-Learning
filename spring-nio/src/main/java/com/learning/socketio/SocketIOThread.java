package com.learning.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/4/19 14:26]
 */
public class SocketIOThread {

	private final String serverUrl;

	private Socket socket;

	private final String num;

	private final IO.Options options = new IO.Options();

	public SocketIOThread(String serverUrl, String num) throws URISyntaxException {
		this.serverUrl = serverUrl;
		this.socket = socket;
		this.num = num;
		this.options.reconnectionAttempts = 10;
		this.options.reconnection = true;
		this.options.timeout = 10000;
		this.options.reconnectionDelay = 1000;
		this.socket = IO.socket(serverUrl, options);
	}

	public void method() {
		//监听自定义msg事件
		socket.on("msg", objects -> System.out.println("client: 收到msg->" + Arrays.toString(objects)));
		//监听自定义订阅事件
		socket.on("sub", objects -> System.out.println("client: " + "订阅成功，收到反馈->" + Arrays.toString(objects)));
		socket.on(Socket.EVENT_CONNECT, objects -> {
			socket.emit("sub", "我是訂閲對象");
			System.out.println("client: " + "连接成功");
		});
		socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
		socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"));
		socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
		socket.connect();
	}
}
