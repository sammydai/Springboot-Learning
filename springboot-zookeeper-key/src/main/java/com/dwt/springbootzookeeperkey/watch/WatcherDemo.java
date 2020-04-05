package com.dwt.springbootzookeeperkey.watch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Package: com.dwt.springbootzookeeperkey.watch
 * @Description:
 * @Author: Sammy
 * @Date: 2020/4/5 14:39
 */

@Service
@Data
@Slf4j
public class WatcherDemo {
	private String workerPath = "/test/listener/remoteNode";

	private String subWorkerPath = "/test/listener/remoteNode/id-";

	@Autowired
	private CuratorFramework client;

	public void testWatcher() {
		try {
			Stat stat = client.checkExists().forPath(workerPath);
			if (stat == null) {
				client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(workerPath, "hellossm".getBytes("UTF-8"));
			} else {
				// String dataUp = "hello world";
				// client.setData().forPath(workerPath, dataUp.getBytes("UTF-8"));
				byte[] payload = client.getData().forPath(workerPath);
				String data = new String(payload,"UTF-8");
				log.error("read data: {}",data);
				// List<String> children = client.getChildren().forPath("/services");
				// for (String child : children) {
				// 	log.error("child:{}",child);
				// }
			}
			Watcher watcher = new Watcher() {
				@Override
				public void process(WatchedEvent watchedEvent) {
					log.info("========>监听到的变化watchedEvent:" + watchedEvent);
				}
			};
			byte[] content = client.getData().usingWatcher(watcher).forPath(workerPath);
			log.info("=========>监听节点的内容:{}",new String(content));
			client.setData().forPath(workerPath, "第1次更改内容".getBytes("UTF-8"));
			client.setData().forPath(workerPath, "第2次更改内容".getBytes("UTF-8"));
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
