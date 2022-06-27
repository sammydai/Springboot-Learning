package com.learning.ioc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;

/**
 * @Package: com.learning.ioc.netty
 * @Description:
 * @Author: Sammy
 * @Date: 2022/6/27 12:59
 */

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.channel().close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("【Client】：ctx" + ctx);
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,server", CharsetUtil.UTF_8));
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		System.out.println("服务器发送的 msg ：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址 ："+ ctx.channel().remoteAddress());
	}
}
