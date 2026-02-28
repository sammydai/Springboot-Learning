package com.dwt.redis.service;


import com.dwt.redis.RedisApplication;
import com.dwt.redis.dto.SeckillOrderDTO;
import com.dwt.redis.entity.SeckillOrder;
import com.dwt.redis.mapper.SeckillMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RedisApplication.class)
class PerformanceTest {

    @Autowired
    private SeckillService seckillService;
    
    @Autowired
    private SeckillMapper seckillMapper;
    
    @Test
    void testGetOrderDetail_Performance() {
        System.out.println("\n=== 性能测试: 多次查询同一订单 ===");
        
        SeckillOrder order = createTestOrder(1001L, 2001L);
        Long orderId = order.getId();
        
        long startTime = System.currentTimeMillis();
        int queryCount = 100;
        
        for (int i = 0; i < queryCount; i++) {
            SeckillOrderDTO result = seckillService.getOrderDetail(orderId);
            assertNotNull(result);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double avgTime = (double) duration / queryCount;
        
        System.out.println("查询" + queryCount + "次，总耗时: " + duration + "ms");
        System.out.println("平均每次查询耗时: " + avgTime + "ms");
        
        assertTrue(avgTime < 100, "单次查询应该小于100ms");
    }
    
    @Test
    void testGetOrderDetail_MultipleOrders() {
        System.out.println("\n=== 性能测试: 查询多个订单 ===");
        
        // 创建100个测试订单
        int orderCount = 100;
        for (int i = 50; i < orderCount; i++) {
            createTestOrder(1000L + i, 2000L + i);
        }
        
        long startTime = System.currentTimeMillis();
        
        // 查询所有订单
        for (int i = 50; i < orderCount; i++) {
            SeckillOrderDTO result = seckillService.getOrderDetail((long) (i + 1));
            assertNotNull(result);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("查询" + orderCount + "个不同订单，总耗时: " + duration + "ms");
        assertTrue(duration < 5000, "查询100个订单应该小于5秒");
    }
    
    private SeckillOrder createTestOrder(Long userId, Long productId) {
        SeckillOrder order = new SeckillOrder();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(2);
        order.setPrice(new BigDecimal("99.99"));
        order.setStatus(0);
        order.setCreateTime(new Date());
        
        seckillMapper.createOrder(order);
        return order;
    }
}