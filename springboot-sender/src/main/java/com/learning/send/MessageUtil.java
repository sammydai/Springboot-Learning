package com.learning.send;


import com.learning.data.EchoFile;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 16:12]
 */
public class MessageUtil {

	public static String process = "x";

	public static ArrayList<ArrayList<EchoFile>> taskInstanceDatas = new ArrayList<ArrayList<EchoFile>>();

	public static ConcurrentHashMap<String, JSONObject> subtaskInstanceDatas = new ConcurrentHashMap<>();

	public static LinkedBlockingQueue<EchoFile> results0 = new LinkedBlockingQueue<>();
	public static LinkedBlockingQueue<EchoFile> results1 = new LinkedBlockingQueue<>();

	public static ConcurrentHashMap<String, Integer> resultTosubkey = new ConcurrentHashMap<>();

	public static LinkedBlockingQueue<EchoFile> getQueue(int queueIndex) {
		if (queueIndex == 0) {
			return MessageUtil.results0;
		} else if (queueIndex == 1) {
			return MessageUtil.results1;
		} else {
			return new LinkedBlockingQueue<EchoFile>();
		}
	}
}
