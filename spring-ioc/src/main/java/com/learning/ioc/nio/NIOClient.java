package com.learning.ioc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Package: com.learning.ioc.nio
 * @Description: NIOClient
 * @Author: Sammy
 * @Date: 2022/6/27 09:26
 */

public class NIOClient {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 6666);
		if (!socketChannel.connect(inetSocketAddress)) {
			while (!socketChannel.finishConnect()) {
				System.out.println("连接中……因为连接需要时间，客户端不会阻塞，可以做其他工作");
			}
		}
		// 如果连接成功就发送数据
        String str = "Hello,NIO";
		ByteBuffer byteBuff = ByteBuffer.wrap(str.getBytes());
		socketChannel.write(byteBuff);
	}
}
