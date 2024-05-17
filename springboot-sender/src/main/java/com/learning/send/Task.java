package com.learning.send;


import com.learning.data.EchoFile;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.Callable;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 16:35]
 */
public class Task implements Callable<Boolean> {

	private int taskCIdx = 0;

	public Task(int idx, int taskCIdx, int sendtask_size) {
		this.taskCIdx = idx;
		EchoClient.logInfo("INIT Task_pkg[" + this.taskCIdx + " size:" + sendtask_size + "]");
	}

	@Override
	public Boolean call() throws Exception {
		EchoClient.logInfo("Q" + this.taskCIdx);
		connect(this.taskCIdx);
		return true;
	}

	private void connect(int taskCIdx) {
		// String NIOTrans = SystemCaches.getDictValue("SystemRunParameters", "NIOTrans").getString("DICTNAME");
		// EventLoopGroup group = NIOTrans.equals("CLOSE") ? new OioEventLoopGroup(1) : new NioEventLoopGroup(1);
		EventLoopGroup group = new NioEventLoopGroup();
		// try {
		Bootstrap b = new Bootstrap();


		// }
		EchoFile ch = null;
		int qsize = 0;
		boolean xflag = true;
		try {
			qsize = MessageUtil.getQueue(this.taskCIdx).size();
			ch = MessageUtil.getQueue(this.taskCIdx).poll();
			if (ch != null) {
				EchoFile exx = new EchoFile();
				exx.TASK_ID = "N/A";
				MessageUtil.getQueue(this.taskCIdx).offer(exx);
			} else {
				xflag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
