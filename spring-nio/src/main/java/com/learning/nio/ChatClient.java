package com.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Package: com.learning.ioc.nio
 * @Description: ChatClient
 * @Author: Sammy
 * @Date: 2022/6/27 09:47
 */

public class ChatClient {
    // 定义相关属性
    // 服务器的IP
    private final String HOST = "127.0.0.1";
    // 服务器的端口
    private final int PORT = 6666;

    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    // 构造器
    public ChatClient() throws IOException {
        // 完成初始化
        selector = Selector.open();
        // 连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        // 设置 非阻塞
        socketChannel.configureBlocking(false);
        // 将 socketChannel 注册到 Selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 得到 username
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + "is OK!");

    }

    // 向服务器发送消息
    public void sendMessage(String message){
        message = username + "说："+ message;
        try {
            // 把 message 写入 buffer
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
            // 读取从服务器端回复的消息
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    public void readmessage(){
        try {
            int select = selector.select();
            if (select > 0){
                // 有事件发生的通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        // 得到相关的通道
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    iterator.remove();
                }
            }else {
                System.out.println("没有可用的通道");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    public static void main(String[] args) throws IOException {
        // 启动客户端
        ChatClient client = new ChatClient();

        // 启动一个线程,每个三秒读取从服务器端读取数据
        new Thread(()->{
            while (true){
                client.readmessage();
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        // 发送数据给服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            client.sendMessage(line);
        }
    }

}


