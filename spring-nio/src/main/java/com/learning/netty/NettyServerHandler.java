package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @Package: com.learning.ioc.netty
 * @Description:
 * @Author: Sammy
 * @Date: 2022/6/27 12:19
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("服务器读取线程的Name : "+Thread.currentThread().getName());
		System.out.println("【Server】： ctx" + ctx);
		System.out.println("Channel和Pipeline的关系");
		Channel channel = ctx.channel();
		ChannelPipeline pipeline = ctx.pipeline();
		// 将 msg 转换成 ByteBuffer
        /*
            说明 :
            1. 注意这个是 ByteBuf ，是 io.netty.buffer 包下的，不是 NIO 下的 Buffer
            2. ByteBuf 比 Buffer 的性能更高一点
         */
        ByteBuf buf = (ByteBuf) msg;
        // 把 buf 转成 UTF8 格式的字符串
        System.out.println("客户端发送的 msg ：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址 ：" + ctx.channel().remoteAddress());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// 把数据写入缓冲区，并刷新缓冲区
        // 一般来说，需要对这个发送的消息进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端",CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.channel().close();
	}
}
