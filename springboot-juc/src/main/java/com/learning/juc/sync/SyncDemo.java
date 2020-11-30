package com.learning.juc.sync;

import com.learning.juc.domain.Datas;
import com.learning.juc.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @Package: com.learning.juc.sync
 * @Description:
 * @Author: Sammy
 * @Date: 2020/11/28 15:38
 */

@RestController
@Slf4j
public class SyncDemo {
	private List<Integer> data = new ArrayList<>();

	@GetMapping("wrong1")
	public int wrong1(@RequestParam(value = "count", defaultValue = "1000000") int count) {
		Datas.reset();
		IntStream.rangeClosed(1, count).parallel().forEach(i -> new Datas().right());
		return Datas.getCounter();
	}

	private void slow() {
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("wrong2")
	public int wrong2() {
		long begin = System.currentTimeMillis();
		IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
			synchronized (this) {
				slow();
				data.add(i);
			}
		});
		log.info("took:{}", System.currentTimeMillis() - begin);
		return data.size();
	}

	@GetMapping("right2")
	public int right2() {
		long begin = System.currentTimeMillis();
		IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
			slow();
			synchronized (this) {
				data.add(i);
			}
		});
		log.info("took:{}", System.currentTimeMillis() - begin);
		return data.size();
	}




}
