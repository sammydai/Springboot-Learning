package com.dwt.redis;

import com.dwt.redis.service.SeckillService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.openjdk.jmh.runner.Runner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/2/12 14:28]
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 2)
@Measurement(iterations = 2, time = 2)
@Threads(20)
@Fork(1)
@State(Scope.Benchmark)
public class SeckillJMHTest {

	private SeckillService seckillService;

	private ConfigurableApplicationContext context;

	private AtomicLong userIdGenerator = new AtomicLong(1_000_000_000L);


	@Setup
	public void setup() {
		context = SpringApplication.run(RedisApplication.class);
		seckillService = context.getBean(SeckillService.class);
		RedisTemplate<String, Object> redisTemplate =
				(RedisTemplate<String, Object>) context.getBean("redisTemplate");

		// 1. 清空该商品的所有购买记录（这是你之前漏掉的关键步骤！）
		String stockKey = "seckill:stock:" + 10001L;
		String purchasedPattern = "seckill:purchased:" + 10001L + ":*";
		redisTemplate.delete(stockKey);
		redisTemplate.keys(purchasedPattern).forEach(key -> redisTemplate.delete(key));

		// 2. 初始化库存为 1亿，绝对不耗尽
		seckillService.initStock(10001L, 100_000_000);

	}

	@TearDown(Level.Trial)
	public void tearDown() {
		context.close();
	}

	@Benchmark
	public Long seckill() {
		long userId = userIdGenerator.incrementAndGet();
		return seckillService.seckill(userId, 10001L);
	}

	public static void main(String[] args) throws Exception {
		// org.openjdk.jmh.Main.main(args);
		Options opt = new OptionsBuilder()
				.include(SeckillJMHTest.class.getSimpleName())
				.addProfiler("jfr")
				.build();
		new Runner(opt).run();

	}

}
