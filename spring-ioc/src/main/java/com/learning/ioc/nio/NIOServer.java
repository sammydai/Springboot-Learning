package com.learning.ioc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Package: com.learning.ioc.nio
 * @Description: NIOServer
 * @Author: Sammy
 * @Date: 2022/6/26 21:43
 */

public class NIOServer {
	public static void main(String[] args) throws IOException {
		// 创建 ServerSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 得到一个 Selector 实例
		Selector selector = Selector.open();
		// 绑定端口，在服务端进行监听
		serverSocketChannel.socket().bind(new InetSocketAddress(6666));
		// 设置为非阻塞
		serverSocketChannel.configureBlocking(false);
		// 把 ServerSocketChannel 注册到 Selector 关心事件为 OP_ACCEPT(连接)
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			if (selector.select(1000)==0) {
				System.out.println("服务器等待了 1 s，无连接");
				continue;
			}
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if (key.isAcceptable()) {
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
				}
				if (key.isReadable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer buffer = (ByteBuffer) key.attachment();
					channel.read(buffer);
					System.out.println("from 客户端——"+new String(buffer.array()));
				}
				keyIterator.remove();
			}
		}
	}
}
