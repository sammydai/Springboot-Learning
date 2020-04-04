package com.dwt.springbootzookeeperkey.config.pros;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Package: com.dwt.springbootzookeeperkey.config.pros
 * @Description:
 * @Author: Sammy
 * @Date: 2020/4/4 23:11
 */
@Data
@ConfigurationProperties(prefix = "zk")
public class ZkPros {

	private String url;

	private int timeout = 1000;

	private int retry = 3;

}
