package com.learning.io.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
		log.info("lines {}", Files.lines(Paths.get("test.txt")).limit(200000).collect(Collectors.toList()).size());
		stopWatch.stop();
		stopWatch.start("read 2000000 lines");
		//使用Files.lines方法读取200万行数据
		log.info("lines {}", Files.lines(Paths.get("test.txt")).limit(2000000).collect(Collectors.toList()).size());
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		AtomicLong atomicLong = new AtomicLong();
		//使用Files.lines方法统计文件总行数
		Files.lines(Paths.get("test.txt")).forEach(line->atomicLong.incrementAndGet());
		log.info("total lines {}", atomicLong.get());
	}
}
