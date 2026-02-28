package com.dwt.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeckillApplicationTests {

    @BeforeEach
    void setUp() {
        // 测试数据初始化
        System.out.println("测试数据初始化");
    }

    @Test
    void contextLoads() {
        // 简单验证Spring Boot能正常启动
        System.out.println("Spring Boot上下文加载成功");
    }
}