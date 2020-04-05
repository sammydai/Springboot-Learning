package com.dwt.springbootzookeeperkey.watch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.dwt.springbootzookeeperkey.watch
 * @Description:
 * @Author: Sammy
 * @Date: 2020/4/5 14:39
 */

@Service
@Data
@Slf4j
public class WatcherNodeDemo {
	private String workerPath = "/test/listener/remoteNode";

	private String subWorkerPath = "/test/listener/remoteNode/id-";

	@Autowired
	private CuratorFramework client;

	public void testWatcher() {
		try {
			Stat stat = client.checkExists().forPath(workerPath);
			if (stat == null) {
				client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(workerPath, "hellossm".getBytes("UTF-8"));
			}
			NodeCache nodeCache = new NodeCache(client, workerPath, false);
			NodeCacheListener listener = new NodeCacheListener() {
				@Override
				public void nodeChanged() throws Exception {
					ChildData childData = nodeCache.getCurrentData();
					log.info("ZNode节点状态改变, path:{}",childData.getPath());
					log.info("ZNode节点状态改变, data:{}",new String(childData.getData(),"UTF-8"));
					log.info("ZNode节点状态改变, stat:{}",childData.getStat());
				}
			};
			nodeCache.getListenable().addListener(listener);
			nodeCache.start();

			client.setData().forPath(workerPath, "第1次更改内容".getBytes("UTF-8"));
			Thread.sleep(1000);

			client.setData().forPath(workerPath, "第2次更改内容".getBytes("UTF-8"));
			Thread.sleep(1000);

			client.setData().forPath(workerPath, "第3次更改内容".getBytes("UTF-8"));
			Thread.sleep(1000);

			client.delete().forPath(workerPath);
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			log.error("=========>创建NodeCache监听失败,path:{}",workerPath);

		}
	}

}
