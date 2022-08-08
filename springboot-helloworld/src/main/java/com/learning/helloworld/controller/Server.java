package com.learning.helloworld.controller;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Package: com.learning.helloworld.controller
 * @Description: TestMethod
 * @Author: Sammy
 * @Date: 2022/6/23 16:10
 */

public class Server {
	public Server() {
		System.out.println("server端在启动，在8233端口保持对客户端连接的监听");
		try {
			//有一个ServerSocket对象对客户的连接的监听、
			// 服务器通过ServerSocket在什么端口对客户机的连接的监听
			ServerSocket serverSocket = new ServerSocket(8233);
			//如果有客户端向服务端的8233端口发起连接请求
			//serverSocket就会接受，返回一个socket对象
			//和客户端保持通信
			//声明一个Socket对象、
			//如果有客户机向服务器的8228端口发起连接的请求
			//那么我们serverSocket.accept()就返回Socket对象
			Socket socket = null;
			int count = 0;
			System.out.println("***服务器即将启动，等待客户端的连接***");
			while (true) {
				socket = serverSocket.accept();
				System.out.println("服务端和客户端握手成功，建立了连接");
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
				count++;//统计客户端的数量
				System.out.println("客户端的数量："+count);
				InetAddress address=socket.getInetAddress();
				System.out.println("当前客户端的IP："+address.getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server();
	}
}
