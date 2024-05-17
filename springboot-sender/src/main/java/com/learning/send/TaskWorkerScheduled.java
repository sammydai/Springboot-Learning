package com.learning.send;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 16:10]
 */
public class TaskWorkerScheduled implements Runnable {
	@Override
	public void run() {
		MessageUtil.taskInstanceDatas.clear();
		MessageUtil.resultTosubkey.clear();
	}
}
