package com.dwt.springbootzookeeperkey.controller;

import com.dwt.springbootzookeeperkey.watch.WatcherDemo;
import com.dwt.springbootzookeeperkey.watch.WatcherNodeDemo;
import com.dwt.springbootzookeeperkey.watch.WatcherPathDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.dwt.springbootzookeeperkey.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/4/5 15:01
 */
@RestController
public class TestController {

	@Autowired
	private WatcherNodeDemo watcherNodeDemo;

	@Autowired
	private WatcherPathDemo watcherPathDemo;

	@RequestMapping("/watch")
	public String test() {
		watcherNodeDemo.testWatcher();
		return "zookeeper watch node";
	}

	@RequestMapping("/watchpath")
	public String testPath() {
		watcherPathDemo.testWatcher();
		return "zookeeper watcherPathDemo";
	}
}

