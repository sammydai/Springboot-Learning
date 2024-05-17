package com.learning.frame.common;

import com.learning.frame.basicframe.ExtClassLoader;
import com.learning.frame.business.IBusinessLogic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.quartz.Scheduler;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 11:51]
 */
public class SystemCaches {
	public static HashMap<String, IBusinessLogic> blClient = new HashMap<>();

	// 热加载部署器
	public static ExtClassLoader classLoader = null;

	// 运行时参数
	public static HashMap<String, Object> caches = new HashMap<>();

	// 服务缓存
	public static HashMap<String, JSONObject> services = new HashMap<>();

	public static Scheduler scheduler = null;

	public static JSONArray schedulerData = new JSONArray();

	public static ExecutorService executorService = Executors.newFixedThreadPool(100);

	public static ExecutorService scanFileExecutorService = Executors.newFixedThreadPool(100);

	public static void put(String key, Object value) {
		try {
			caches.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) {
		return caches.get(key);
	}

	public static Object removeCaches(String key) {
		return caches.remove(key);
	}

	public static void resetCaches() {
		caches = null;
		caches = new HashMap<>();
	}

	public static void putServices(String key, JSONObject value) {
		services.put(key, value);
	}

	public static JSONObject getServices(String key) {
		return services.get(key);
	}

	public static JSONObject removeServices(String key) {
		return services.remove(key);
	}

	public static void resetServices() {
		services = null;
		services = new HashMap<>();
	}

}
