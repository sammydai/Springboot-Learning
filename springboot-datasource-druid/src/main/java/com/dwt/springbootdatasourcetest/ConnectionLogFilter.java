package com.dwt.springbootdatasourcetest;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @Package: com.dwt.springbootdatasourcetest
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/22 17:40
 */

@Slf4j
public class ConnectionLogFilter extends FilterEventAdapter {
	@Override
	public void connection_connectBefore(FilterChain chain, Properties info) {
		log.info("BEFORE CONNECTION!");
	}

	@Override
	public void connection_connectAfter(ConnectionProxy connection) {
		log.info("AFTER CONNECTION!");
	}
}
