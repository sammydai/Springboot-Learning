package com.learning.helloworld.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Package: com.learning.helloworld.config;
 * @Description:
 * @Author: Sammy
 * @Date: 2020/6/14 17:11
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

	private int serverPort;

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://"+address.getHostAddress() +":"+this.serverPort;
    }

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		this.serverPort = event.getWebServer().getPort();
	}
}
