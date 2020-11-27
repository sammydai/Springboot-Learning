package com.learning.juc.threadlocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.learning.juc.threadlocal
 * @Description: 在finally代码块中删除ThreadLocal中的数据
 * @Author: Sammy
 * @Date: 2020/11/27 14:22
 */

@RestController
public class ThreadLocalDemo {

	private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

	@GetMapping("wrongthredlocal")
	public Map wrong(@RequestParam("userId") Integer userId) {
		String before = Thread.currentThread().getName() + ":" + currentUser.get();
		currentUser.set(userId);
		String after = Thread.currentThread().getName() + ":" + currentUser.get();
		Map<String, String> result = new HashMap<>();
		result.put("before", before);
		result.put("after", after);
		return result;
	}
}
