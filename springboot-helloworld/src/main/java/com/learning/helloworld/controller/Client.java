package com.learning.helloworld.controller;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Package: com.learning.helloworld.controller
 * @Description: Client for Socket
 * @Author: Sammy
 * @Date: 2022/6/25 12:18
 */

public class Client {
	public Client() {
		System.out.println("客户端正在启动");
		try {
			//Socket clientsocket = new Socket("localhost", 8233);
			Socket clientsocket =new Socket();
			clientsocket.connect(new InetSocketAddress("localhost",8233),60000);
			OutputStream os = clientsocket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(os);
			printWriter.write("用户名：alice;密码：789");
			printWriter.flush();
			clientsocket.shutdownOutput();
			InputStream is = clientsocket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是客户端，服务器说："+info);
			}
			os.close();
			printWriter.close();
			is.close();
			br.close();
			clientsocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Client();
	}
}
