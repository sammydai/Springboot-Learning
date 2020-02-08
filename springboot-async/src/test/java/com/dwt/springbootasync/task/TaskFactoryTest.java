package com.dwt.springbootasync.task;

import com.dwt.springbootasync.SpringbootAsyncApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: Sammy
 * @Time: 2020/2/8 01:08
 */
@Slf4j
public class TaskFactoryTest extends SpringbootAsyncApplicationTests {

	@Autowired
	private TaskFactory task;

	/**
	 * @Description 测试异步任务
	 * @Author  Sammy
	 * @Date   2020/2/8 01:21
	 * @Param
	 * @Return      void
	 * @Exception
	 *
	 */


	@Test
	public void asyncTaskTest() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		Future<Boolean> asyncTask1 = task.asyncTask1();
		Future<Boolean> asyncTask2 = task.asyncTask2();
		Future<Boolean> asyncTask3 = task.asyncTask3();

		asyncTask1.get();
		asyncTask2.get();
		asyncTask3.get();

		long end = System.currentTimeMillis();
		log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
	}

	/**
	 * 测试同步任务
	 */
	@Test
	public void taskTest() throws InterruptedException {
		long start = System.currentTimeMillis();
		task.task1();
		task.task2();
		task.task3();
		long end = System.currentTimeMillis();

		log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));
	}


}
