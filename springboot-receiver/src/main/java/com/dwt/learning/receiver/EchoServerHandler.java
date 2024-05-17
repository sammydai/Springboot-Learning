package com.dwt.learning.receiver;

import com.dwt.learning.data.EchoFile;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/17 16:57]
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String path;
		byte[] bytes = null;
		RandomAccessFile randomAccessFile = null;
		if (msg instanceof EchoFile) {
			EchoFile ef = (EchoFile) msg;
			bytes = ef.bytes;
			path = ef.DEST_FILE_PATH;
			File file = new File(path);
			randomAccessFile = new RandomAccessFile(file, "rw");
			randomAccessFile.seek(0);
			randomAccessFile.write(bytes);
			randomAccessFile.close();
			randomAccessFile = null;
		} else {
			ctx.writeAndFlush("BAD!!Not My File");
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
