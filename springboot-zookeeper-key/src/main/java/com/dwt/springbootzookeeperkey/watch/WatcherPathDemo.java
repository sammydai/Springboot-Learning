package com.dwt.springbootzookeeperkey.watch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
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
public class WatcherPathDemo {
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

			PathChildrenCache cache = new PathChildrenCache(client, workerPath, true);
			PathChildrenCacheListener listener = new PathChildrenCacheListener() {
				@Override
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					ChildData data = event.getData();
					switch (event.getType()) {
						case CHILD_ADDED:
							log.info("子节点增加, path:{},data:{}", data.getPath(), new String(data.getData(), "UTF-8"));
							break;
						case CHILD_UPDATED:
							log.info("子节点更新, path:{},data:{}", data.getPath(), new String(data.getData(), "UTF-8"));
							break;
						case CHILD_REMOVED:
							log.info("子节点删除, path:{},data:{}", data.getPath(), new String(data.getData(), "UTF-8"));
							break;
						default:
							break;
					}
				}
			};
			cache.getListenable().addListener(listener);
			cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
			Thread.sleep(1000);

			for (int i = 0; i < 3; i++) {
				client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(subWorkerPath + i, ("msg"+i).getBytes("UTF-8"));
			}
			Thread.sleep(1000);

			for (int i = 0; i < 3; i++) {
				client.delete().forPath(subWorkerPath + i);
			}
		} catch (Exception e) {
			log.error("=========>PathCache监听失败,path:{}",workerPath);

		}
	}

}
