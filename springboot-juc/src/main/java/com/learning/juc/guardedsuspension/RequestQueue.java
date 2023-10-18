package com.learning.juc.guardedsuspension;

import java.util.LinkedList;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 12:08]
 */
public class RequestQueue {
	private final LinkedList<Request> queue = new LinkedList<Request>();

	public synchronized Request getRequest() {
		while (queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return queue.removeFirst();
	}

	public synchronized void putRequest(Request request) {
		queue.addLast(request);
		notifyAll();
	}
}
