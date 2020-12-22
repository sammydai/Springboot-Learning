package com.learning.actuator.info;

import com.learning.actuator.health.ThreadPoolProvider;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Package: com.learning.actuator.info
 * @Description: ThreadPoolInfoContributor
 * @Author: Sammy
 * @Date: 2020/12/22 22:23
 */
@Component
public class ThreadPoolInfoContributor implements InfoContributor {
	private static Map thredPoolInfo(ThreadPoolExecutor threadPool) {
		Map<String, Object> info = new HashMap<>();
		info.put("poolSize", threadPool.getPoolSize());
		info.put("corePoolSize", threadPool.getCorePoolSize());
		info.put("largestPoolSize", threadPool.getLargestPoolSize());
        info.put("maximumPoolSize", threadPool.getMaximumPoolSize());
        info.put("completedTaskCount", threadPool.getCompletedTaskCount());
        return info;
	}

	@Override
	public void contribute(Info.Builder builder) {
		builder.withDetail("demoThreadPool", thredPoolInfo(ThreadPoolProvider.getDemoThreadPool()));
		builder.withDetail("ioThreadPool", thredPoolInfo(ThreadPoolProvider.getIoThreadPool()));
	}
}
