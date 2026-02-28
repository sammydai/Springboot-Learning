package com.dwt.redis;

import com.dwt.redis.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/2/3 09:50]
 */
@Slf4j
@SpringBootTest
public class SeckillConcurrencyTest {

	@Autowired
	private SeckillService seckillService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static final Long TEST_PRODUCT_ID = 10001L;
	private static final int  INITIAL_STOCK = 1000;
	private static final int  THREAD_COUNT = 1000;
	private static final int  PER_USER_ATTEMPTS = 2;

	@BeforeEach
	public void setUp() {
		cleanTestData();
		log.info("初始化商品库存");
		seckillService.initStock(TEST_PRODUCT_ID, INITIAL_STOCK);
		log.info("初始化商品 {} 库存为: {}", TEST_PRODUCT_ID, INITIAL_STOCK);
	}

	@Test
	public void cleanTestData() {
		// 清理Redis中的测试数据
		String stockKey = "seckill:stock:" + TEST_PRODUCT_ID;
		String purchasedPattern = "seckill:purchased:" + TEST_PRODUCT_ID + ":*";
		redisTemplate.delete(stockKey);
		redisTemplate.keys(purchasedPattern).forEach(key -> redisTemplate.delete(key));
	}

	/**
	 * 测试基本多线程秒杀功能。
	 * 该方法模拟多个用户并发进行秒杀操作，验证系统的并发处理能力和数据一致性。
	 *
	 * 主要步骤包括：
	 * 1. 初始化统计变量（成功数、失败数、总耗时等）。
	 * 2. 创建固定大小的线程池，并使用 CountDownLatch 控制线程同步。
	 * 3. 每个线程尝试调用秒杀服务，记录结果并更新统计数据。
	 * 4. 所有线程完成后，输出测试结果并验证库存是否正确减少。
	 *
	 * @throws InterruptedException 当线程等待过程中被中断时抛出
	 */
	@Test
	public void testBasicConcurrentSeckill() throws InterruptedException {
		log.info("\n=== 测试1：基本多线程秒杀 ===");

		// 统计结果
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failureCount = new AtomicInteger(0);
		AtomicLong totalTime = new AtomicLong(0);
		List<Long> orderIds = new CopyOnWriteArrayList<>();

		// 创建线程池
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch endLatch = new CountDownLatch(THREAD_COUNT);

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < THREAD_COUNT; i++) {
			final long userId = i + 10000L;
			executorService.submit(() -> {
				try {
					// 等待所有线程同时开始
					startLatch.await();
					long threadStart = System.currentTimeMillis();
					Long orderId = seckillService.seckill(userId, TEST_PRODUCT_ID);
					orderIds.add(orderId);
					successCount.incrementAndGet();
					log.info("用户 {} 秒杀成功，订单ID: {}", userId, orderId);
				} catch (RuntimeException e) {
					failureCount.incrementAndGet();
					log.debug("用户 {} 秒杀失败: {}", userId, e.getMessage());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					failureCount.incrementAndGet();
				} finally {
					endLatch.countDown();
				}

			});

		}
		// 同时释放所有线程
		startLatch.countDown();

		// 等待所有线程执行完成
		boolean completed = endLatch.await(30, TimeUnit.SECONDS);
		long totalDuration = System.currentTimeMillis() - startTime;

		log.info("=== 测试结果 ===");
		log.info("并发用户数: " + THREAD_COUNT);
		log.info("商品初始库存: " + INITIAL_STOCK);
		log.info("秒杀成功数量: " + successCount.get());
		log.info("秒杀失败数量: " + failureCount.get());
		log.info("总耗时: " + totalDuration + "ms");
		log.info("平均响应时间: " + (totalTime.get() / THREAD_COUNT) + "ms");
		log.info("订单生成数量: " + orderIds.size());

		// 验证结果
		Integer remainingStock = (Integer) redisTemplate.opsForValue().get("seckill:stock:" + TEST_PRODUCT_ID);
		assertEquals(INITIAL_STOCK - successCount.get(), remainingStock, "库存应为0");
		executorService.shutdown();
		log.info("基本多线程秒杀测试通过");
	}

	/**
	 * 测试基本多线程秒杀功能。
	 * 该方法模拟多个用户并发进行秒杀操作，验证系统的并发处理能力和数据一致性。
	 *
	 * 主要步骤包括：
	 * 1. 初始化统计变量（成功数、失败数、总耗时等）。
	 * 2. 创建固定大小的线程池，并使用 CountDownLatch 控制线程同步。
	 * 3. 每个线程尝试调用秒杀服务，记录结果并更新统计数据。
	 * 4. 所有线程完成后，输出测试结果并验证库存是否正确减少。
	 *
	 * @throws InterruptedException 当线程等待过程中被中断时抛出
	 */
	@Test
	void testRealisticSeckillScenario() throws InterruptedException, BrokenBarrierException {
		log.info("\n=== 测试2：真实秒杀场景（含重复尝试） ===");
		int threadCount = 500;
		int attemptsPerUser = 3;

		// 统计结果
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger duplicateAttempts = new AtomicInteger(0);
		AtomicInteger outOfStockAttempts = new AtomicInteger(0);
		AtomicInteger otherErrors = new AtomicInteger(0);
		List<String> errorMessages = new CopyOnWriteArrayList<>();

		// 创建线程池
		int poolSize = Math.min(threadCount, 500);
		// ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(poolSize, poolSize, 60L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
		log.info("线程池配置：核心线程数 = {}, 最大线程数 = {}, 队列 = 无界", poolSize, poolSize);
		CountDownLatch latch = new CountDownLatch(threadCount);
		CyclicBarrier barrier = new CyclicBarrier(threadCount + 1); //+1 表示主线程

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			final long userId = 20000L + i;
			executorService.submit(() -> {
				log.debug("用户[{}] 线程已提交，等待屏障...", userId);
				try {
					// 等待所有线程就绪
					barrier.await();

					// 每个用户尝试多次
					for (int attempt = 0; attempt < attemptsPerUser; attempt++) {
						try {
							Long orderId = seckillService.seckill(userId, TEST_PRODUCT_ID);
							successCount.incrementAndGet();
							log.info("用户 {} 第{} 次秒杀成功，订单ID: {}", userId, attempt + 1, orderId);
							break;
						} catch (RuntimeException e) {
							String errorMsg = e.getMessage();
							if (errorMsg.contains("已经购买过")) {
								duplicateAttempts.incrementAndGet();
								break; // 已购买过，不再尝试
							} else if (errorMsg.contains("已售罄")) {
								outOfStockAttempts.incrementAndGet();
								break; // 已售罄，不再尝试
							} else {
								otherErrors.incrementAndGet();
								errorMessages.add("用户" + userId + ": " + errorMsg);
							}
						}
						// 模拟用户重试间隔
						if (attempt < attemptsPerUser - 1) {
							Thread.sleep(10 + (long) (Math.random() * 20));
						}
					}
				} catch (Exception e) {
					otherErrors.incrementAndGet();
					errorMessages.add("用户" + userId + "异常: " + e.getMessage());
				}finally {
					latch.countDown();
				}
			});
		}
		// 等待所有线程就绪，然后同时开始
		barrier.await();
		// 等待所有线程执行完成，然后总结
		boolean completed = latch.await(60, TimeUnit.SECONDS);
		long totalDuration = System.currentTimeMillis() - startTime;

		// 分析结果
		System.out.println("========== 详细结果分析 ==========");
		System.out.println("并发用户数: " + threadCount);
		System.out.println("每人尝试次数: " + attemptsPerUser);
		System.out.println("总尝试次数: " + threadCount * attemptsPerUser);
		System.out.println("秒杀成功数量: " + successCount.get());
		System.out.println("重复尝试次数: " + duplicateAttempts.get());
		System.out.println("库存不足次数: " + outOfStockAttempts.get());
		System.out.println("其他错误次数: " + otherErrors.get());
		System.out.println("总耗时: " + totalDuration + "ms");
		System.out.println("QPS（成功）: " + (successCount.get() * 1000.0 / totalDuration));

		if (!errorMessages.isEmpty()) {
			System.out.println("错误信息，前10个:");
			errorMessages.stream().limit(10).forEach(System.out::println);
		}
		assertTrue( completed,"测试应该在60秒内完成");

		int expectedSuccess = Math.min(INITIAL_STOCK, threadCount);
		assertEquals(expectedSuccess, successCount.get(),
				String.format("成功数量应等于%d（库存%d，用户%d）", expectedSuccess, INITIAL_STOCK, threadCount));
		assertEquals(0, otherErrors.get(), "不应有其他类型的错误");
		// 验证剩余库存
		Integer remainingStock = (Integer) redisTemplate.opsForValue()
				.get("seckill:stock:" + TEST_PRODUCT_ID);
		assertNotNull(remainingStock);
		assertEquals(INITIAL_STOCK - expectedSuccess, remainingStock, "剩余库存不正确");

		assertEquals(0,otherErrors.get(),"不应有其他类型的错误");
		executorService.shutdown();
		System.out.println("✅ 真实秒杀场景测试通过");
	}
}
