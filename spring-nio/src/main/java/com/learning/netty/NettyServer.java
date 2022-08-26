package com.learning.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Package: com.learning.ioc.netty
 * @Description: NettyServer
 * @Author: Sammy
 * @Date: 2022/6/27 12:09
 */

public class NettyServer {
	public static void main(String[] args) throws InterruptedException {
		// 创建 BossGroup 和 WorkerGroup
        /*
            说明
            1. 创建两个线程组 BossGroup 和 WorkerGroup
            2. BossGroup 只处理连接请求
            3. WorkerGroup 处理真正客户端的业务
            4. 运行时，这两个都是无限循环
         */
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {


			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)// 设置两个线程组
                    .channel(NioServerSocketChannel.class)// 使用 NioServerSocketChannel 作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)// 设置线程队列等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)// 设置连接保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {// 给 workerGroup 的 NioEventLoop 对应的管道（Pipeline）设置处理器
						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline()
									.addLast(new NettyServerHandler());
						}
					});
			System.out.println("服务器准备好了……");
			// 绑定一个端口，并且同步。生成了一个 ChannelFuture 对象
            // 这里就已经启动了服务器
			ChannelFuture channelFuture = bootstrap.bind(6668).sync();
			// 对 关闭通道 进行监听
            // 这里只是监听，只有关闭通道时才进行处理，这句话不是直接关闭了通道
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
