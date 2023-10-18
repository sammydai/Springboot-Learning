package com.learning.juc.guardedsuspension;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 12:09]
 */

public class Main {
	public static void main(String[] args) {
		RequestQueue requestQueue = new RequestQueue();
		new ClientThread(requestQueue, "Alice", 3141592L).start();
		new ServerThread(requestQueue, "Bobby", 6535897L).start();
	}
}
