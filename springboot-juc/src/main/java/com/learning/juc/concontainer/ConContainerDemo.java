package com.learning.juc.concontainer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @Package: com.learning.juc.concontainer
 * @Description: ConContainer
 * @Author: Sammy
 * @Date: 2020/11/27 14:24
 */

@Slf4j
@RestController
public class ConContainerDemo {
	private static final int THREAD_COUNT = 10;
	private static final int ITEM_COUNT = 100;

	private ConcurrentHashMap<String,Long> getData(int count){
		return LongStream.rangeClosed(1, count)
				.boxed()
				.collect(Collectors.toConcurrentMap(
						i -> UUID.randomUUID().toString(),
						Function.identity(), (o1, o2) ->
								o1, ConcurrentHashMap::new));
	}

	@GetMapping("wrongcon")
	public String wrong() throws InterruptedException {
		ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
		log.info("init size{}", concurrentHashMap.size());
		ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
		forkJoinPool.execute(()->{
			IntStream.rangeClosed(1,10).parallel().forEach(i->{
				int gap = ITEM_COUNT - concurrentHashMap.size();
				log.info("gap size{}", gap);
				concurrentHashMap.putAll(getData(gap));
			});
		});
		forkJoinPool.shutdown();
		forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
		log.info("finish size{}", concurrentHashMap.size());
		return "OK";
	}
}
