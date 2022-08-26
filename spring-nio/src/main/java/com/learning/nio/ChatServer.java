package com.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Package: com.learning.ioc.nio
 * @Description: ChatServer
 * @Author: Sammy
 * @Date: 2022/6/27 09:56
 */

public class ChatServer {

	// 定义属性
    private Selector selector;
    private ServerSocketChannel listenerChannell;
    private static final int PORT = 6666;

	public ChatServer() {
		try {
			// 得到选择器
            selector = Selector.open();
            // 得到 ServerSocketChannel
            listenerChannell = ServerSocketChannel.open();
            // 绑定端口
            listenerChannell.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞
            listenerChannell.configureBlocking(false);
            // 把 listenerChannell 注册到 Selector 中，关注连接事件
            listenerChannell.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listen(){
		try {
			while (true) {
				int select = selector.select();
				if (select > 0) {
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						if (key.isAcceptable()) {
							SocketChannel socketChannel = listenerChannell.accept();
							socketChannel.configureBlocking(false);
							socketChannel.register(selector, SelectionKey.OP_READ);
							System.out.println(socketChannel.getRemoteAddress() + "上线了~");
						}
						if (key.isReadable()) {
							read(key);
						}
						iterator.remove();
					}
				} else {
					System.out.println("等待中……");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void read(SelectionKey key) {
		SocketChannel socketChannel = null;
		try {
			socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
			int read = socketChannel.read(buffer);
			if (read > 0) {
				String s = new String(buffer.array());
                System.out.println("【服务端】收到客户端消息："+ s);
				sendMessageToOther(s, socketChannel);
			}
		} catch (IOException e) {
			 try {
                System.out.println(socketChannel.getRemoteAddress() + "离线了~");
                // 取消注册
                key.channel();
                // 关闭通道
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
		}
	}

	private void sendMessageToOther(String s, SocketChannel selfChannnel) throws IOException {
		System.out.println("服务器转发消息中……");
		for (SelectionKey key : selector.keys()) {
			Channel channel = key.channel();
			if (channel instanceof SocketChannel && channel!=selfChannnel) {
				SocketChannel dest = (SocketChannel) channel;
				ByteBuffer buff = ByteBuffer.wrap(s.getBytes());
				dest.write(buff);
			}
		}
	}

	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		chatServer.listen();
	}
}
