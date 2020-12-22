package com.learning.actuator.health;

import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Package: com.learning.actuator.health
 * @Description: ThreadPoolsHealthContributor
 * @Author: Sammy
 * @Date: 2020/12/22 21:24
 */
@Component
public class ThreadPoolsHealthContributor implements CompositeHealthContributor {

	private Map<String, HealthContributor> contributors = new HashMap<>();

	public ThreadPoolsHealthContributor() {
		//对应ThreadPoolProvider中定义的两个线程池
		this.contributors.put("demoThreadPool", new ThreadPoolHealthIndicator(ThreadPoolProvider.getDemoThreadPool()));
		this.contributors.put("ioThreadPool", new ThreadPoolHealthIndicator(ThreadPoolProvider.getIoThreadPool()));
	}

	@Override
	public HealthContributor getContributor(String name) {
		return this.contributors.get(name);
	}

	@Override
	public Iterator<NamedContributor<HealthContributor>> iterator() {
		return contributors.entrySet().stream()
				.map((entry) -> NamedContributor.of(entry.getKey(), entry.getValue())).iterator();
	}
}
