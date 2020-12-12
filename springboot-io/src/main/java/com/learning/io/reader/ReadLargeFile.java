package com.learning.io.reader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.util.stream.Collectors.toList;

/**
 * @Package: com.learning.io.reader
 * @Description: Read Large File
 * @Author: Sammy
 * @Date: 2020/12/12 17:40
 */
@Slf4j
public class ReadLargeFile {

	public void readLargeFile() throws IOException {
		//输出文件大小
		log.info("file size:{}", Files.size(Paths.get("test.txt")));
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("read 200000 lines");
		//使用Files.lines方法读取20万行数据
		log.info("lines {}", Files.lines(Paths.get("test.txt")).limit(200000).collect(toList()).size());
		stopWatch.stop();
		stopWatch.start("read 2000000 lines");
		//使用Files.lines方法读取200万行数据
		log.info("lines {}", Files.lines(Paths.get("test.txt")).limit(2000000).collect(toList()).size());
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		AtomicLong atomicLong = new AtomicLong();
		//使用Files.lines方法统计文件总行数
		Files.lines(Paths.get("test.txt")).forEach(line->atomicLong.incrementAndGet());
		log.info("total lines {}", atomicLong.get());
	}

	public void generateFile() throws IOException {
		Files.write(Paths.get("demo.txt"), IntStream.rangeClosed(1,10).mapToObj(i-> UUID.randomUUID().toString()).
				collect(toList()), Charset.forName("UTF-8"),CREATE, TRUNCATE_EXISTING);
	}

	/**
	 * Too many open files
	 */
	public void fileDescription() throws IOException {
		LongAdder longAdder = new LongAdder();
		IntStream.rangeClosed(1,1000000).forEach(i->{
			try {
				Files.lines(Paths.get("demo.txt")).forEach(line->longAdder.increment());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		log.info("total : {}",longAdder.longValue());
	}

	/**
	 * try-with-resource
	 * 执行时间好长：27s
	 */
	public void fileDescriptionSource() throws IOException {
		LongAdder longAdder = new LongAdder();
		IntStream.rangeClosed(1,1000000).forEach(i->{
			try (Stream<String> lines = Files.lines(Paths.get("demo.txt"))) {
				lines.forEach(line->longAdder.increment());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		log.info("total : {}",longAdder.longValue());
	}
}
