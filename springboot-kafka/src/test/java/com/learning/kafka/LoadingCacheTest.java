// package com.learning.kafka;
//
// import com.google.common.cache.CacheBuilder;
// import com.google.common.cache.CacheLoader;
// import com.google.common.cache.LoadingCache;
// import lombok.extern.slf4j.Slf4j;
// import org.junit.Test;
//
//
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.atomic.LongAdder;
//
// /**
//  * [一句话描述该类的功能]
//  *
//  * @author : [Sammy]
//  * @version : [v1.0]
//  * @createTime : [2023/8/11 10:57]
//  */
// @Slf4j
// public class LoadingCacheTest {
//
// 	/**
// 	 * 自增
// 	 */
// 	private static LongAdder ADDER = new LongAdder();
//
// 	@Test
// 	public void test() throws InterruptedException {
// 		LoadingCache<String, String> loadingCache
// 				//CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
// 				= CacheBuilder.newBuilder()
// 				//设置并发级别为8，并发级别是指可以同时写缓存的线程数
// 				.concurrencyLevel(8)
// 				//设置写缓存后60秒过期
// 				.expireAfterWrite(60, TimeUnit.SECONDS)
// 				//设置写缓存后30秒刷新
// 				.refreshAfterWrite(60, TimeUnit.MINUTES)
// 				//设置缓存容器的初始容量为5
// 				.initialCapacity(5)
// 				//设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
// 				.maximumSize(100)
// 				//设置要统计缓存的命中率
// 				.recordStats()
// 				//设置缓存的移除通知
// 				.removalListener(notification -> log.info(notification.getKey() + " 被移除了，原因： " + notification.getCause()))
// 				//build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
// 				.build(new CacheLoader<String, String>() {
//
// 					@Override
// 					public String load(String key) {
// 						// TODO 这里是如果使用loadingCache.get(key)或者这里是如果使用loadingCache.getUnchecked(key)获取
// 						// TODO 残存中数据时,如果为空，则自动会放入LoadingCache内存缓存中，return值就是放入内存缓存中的值
// 						// TODO 同时设置的expireAfterWrite缓存失效策略，超过过期时间，也会自动将这个方法return数据加入放入内存缓存
//
// 						// TODO 此处需要注意就要使用loadingCache.getIfPresent(key)不会加载load方法，就是本方法，就算使用PUT方法
// 						// TODO 例如:
// 						//  String lucky = (String) loadingCache.getUnchecked("lucky");
// 						//  Object aa = loadingCache.getIfPresent("AA");
// 						//  if (aa == null) {
// 						//     loadingCache.put("AA", "A--------------------------");
// 						//  } else {
// 						//     System.out.println("res = " + aa);
// 						//  }
// 						//   System.out.println("数据库获取:" + key + ", load");
// 						// TODO 此时若同时设置的expireAfterWrite缓存失效策略，超过过期时间还是放入是本方法的return结果
//
// 						// TODO 这里可以集合数据库做数据获取，然后返回就是放入缓存数据库中的数据值
// 						ADDER.increment();
// 						return "Hello Guava Cache " + key + "load" + ADDER.intValue();
// 					}
//
// 				});
//
// 		while (true) {
//
// 			String lucky = (String) loadingCache.getUnchecked("lucky");
// 			System.out.println(lucky);
//
// 			Object aa = loadingCache.getIfPresent("AA");
// 			if (aa == null) {
// 				loadingCache.put("AA", "A--------------------------");
// 			} else {
// 				System.out.println("res = " + aa);
// 			}
//
// 			System.out.println("缓存命中率:" + loadingCache.stats().hitCount());
// 			System.out.println("加载新值的平均时间，单位为毫秒:" + loadingCache.stats().averageLoadPenalty() / 10000);
// 			System.out.println("缓存项被回收的总数，不包括显式清除:" + loadingCache.stats().evictionCount());
// 			Thread.sleep(1000);
// 			System.out.println("---------------------------------------------------------");
// 		}
// 	}
// }
//
//
