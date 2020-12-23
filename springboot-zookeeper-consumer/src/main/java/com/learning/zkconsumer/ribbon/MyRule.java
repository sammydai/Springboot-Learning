package com.learning.zkconsumer.ribbon;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;

/**
 * @Package: com.learning.zkconsumer.ribbon
 * @Description: Ribbon Rule
 * @Author: Sammy
 * @Date: 2020/12/21 14:13
 */

public class MyRule extends RoundRobinRule{
	@Override
	public void setLoadBalancer(ILoadBalancer lb) {
		System.out.println("MyRule setLoadBalancer ...");
		super.setLoadBalancer(lb);
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		System.out.println("MyRule getLoadBalancer ...");
		return super.getLoadBalancer();
	}

	@Override
	public Server choose(Object key) {
		System.out.println("MyRule choose " + key + " ...");
		return super.choose(key);
	}
}
