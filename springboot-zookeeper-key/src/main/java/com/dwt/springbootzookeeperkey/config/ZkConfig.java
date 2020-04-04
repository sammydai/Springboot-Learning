package com.dwt.springbootzookeeperkey.config;

import com.dwt.springbootzookeeperkey.config.pros.ZkPros;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.dwt.springbootzookeeperkey.config.pros
 * @Description:
 * @Author: Sammy
 * @Date: 2020/4/4 23:15
 */

@Configuration
@EnableConfigurationProperties(ZkPros.class)
public class ZkConfig {

	private final ZkPros zkPros;

	public ZkConfig(ZkPros zkPros) {
		this.zkPros = zkPros;
	}

	@Bean
	public CuratorFramework curatorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(zkPros.getTimeout(), zkPros.getRetry());
		CuratorFramework client = CuratorFrameworkFactory.newClient(zkPros.getUrl(), retryPolicy);
		client.start();
		return client;
	}
}
