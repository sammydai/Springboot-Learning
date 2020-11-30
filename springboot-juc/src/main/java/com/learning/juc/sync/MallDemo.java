// package com.learning.juc.sync;
//
// import com.learning.juc.domain.Item;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.concurrent.ThreadLocalRandom;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.locks.ReentrantLock;
// import java.util.stream.IntStream;
//
// /**
//  * @Package: com.learning.juc.sync
//  * @Description: Mall Demo
//  * @Author: Sammy
//  * @Date: 2020/11/28 16:38
//  */
// @RestController
// @Slf4j
// public class MallDemo {
// 	private static List<Item> items = new ArrayList<>();
//
// 	public List<Item> createCart() {
// 		return IntStream.rangeClosed(1, 3).mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(items.size()));
// 	}
//
// 	public boolean createOrder(List<Item> order){
// 		List<ReentrantLock> locks = new ArrayList<>();
// 		for (Item item : order) {
// 			try {
// 				if (item.getLock().tryLock(10, TimeUnit.SECONDS)) {
// 					locks.add(item.getLock());
// 				} else {
// 					locks.forEach(ReentrantLock::unlock);
// 					return false;
// 				}
// 			} catch (InterruptedException e) {
// 				e.printStackTrace();
// 			}
// 		}
// 		try {
// 			order.forEach(item -> item.setRemaining(item.getRemaining() - 1) );
// 		}finally {
// 			locks.forEach(ReentrantLock::unlock);
// 		}
// 		return true;
// 	}
//
// 	@GetMapping("wrong3")
// 	public void wrong3() {
// 		long begin = System.currentTimeMillis();
// 		long success = IntStream.rangeClosed(1, 100).parallel().mapToObj(i -> {
// 			List<Item> cart = createCart();
// 			return createOrder(cart);
// 		}).filter(result -> result).count();
// 		// log.info("success:{} totalRemaining:{} took:{}ms items:{}",
// 		// 		success,items);
// 	}
// }
