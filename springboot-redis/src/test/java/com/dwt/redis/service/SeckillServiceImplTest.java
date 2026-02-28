package com.dwt.redis.service;


import com.dwt.redis.RedisApplication;
import com.dwt.redis.dto.SeckillOrderDTO;
import com.dwt.redis.entity.SeckillOrder;
import com.dwt.redis.mapper.SeckillMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RedisApplication.class)
class SeckillServiceImplTest {

    @Autowired
    private SeckillService seckillService;
    
    @Autowired
    private SeckillMapper seckillMapper;
    
    /**
     * 测试getOrderDetail方法 - 订单存在的情况
     */
    @Test
    void testGetOrderDetail_OrderExists() {
        // 1. 创建测试订单
        SeckillOrder order = new SeckillOrder();
        order.setUserId(1001L);
        order.setProductId(2001L);
        order.setQuantity(2);
        order.setPrice(new BigDecimal("99.99"));
        order.setStatus(0);
        order.setCreateTime(new Date());
        
        seckillMapper.createOrder(order);
        Long orderId = order.getId();
        System.out.println("创建测试订单ID: " + orderId);
        
        // 2. 测试getOrderDetail方法
        SeckillOrderDTO result = seckillService.getOrderDetail(orderId);
        
        // 3. 验证结果
        assertNotNull(result);
        assertEquals(orderId, result.getId());
        assertEquals(1001L, result.getUserId());
        assertEquals(2001L, result.getProductId());
        assertEquals("秒杀商品-2001", result.getProductName());
        assertEquals(new BigDecimal("199.98"), result.getTotalAmount());
        assertEquals("待支付", result.getStatusDesc());
        
        System.out.println("测试通过: getOrderDetail返回正确的订单详情");
    }
    
    /**
     * 测试getOrderDetail方法 - 订单不存在的情况
     */
    @Test
    void testGetOrderDetail_OrderNotExists() {
        // 测试不存在的订单ID
        SeckillOrderDTO result = seckillService.getOrderDetail(999999L);
        
        // 应该返回null
        assertNull(result);
        
        System.out.println("测试通过: 不存在的订单返回null");
    }
    
    /**
     * 测试checkResult方法
     */
    @Test
    void testCheckResult() {
        // 1. 创建测试订单
        SeckillOrder order = new SeckillOrder();
        order.setUserId(1002L);
        order.setProductId(2002L);
        order.setQuantity(1);
        order.setPrice(new BigDecimal("149.99"));
        order.setStatus(1); // 已支付状态
        order.setCreateTime(new Date());
        order.setPayTime(new Date());
        
        seckillMapper.createOrder(order);
        
        // 2. 测试checkResult
        String result = seckillService.checkResult(order.getId());
        
        // 3. 验证
        assertNotNull(result);
        assertEquals("已支付", result);
        
        System.out.println("测试通过: checkResult返回正确的状态");
    }
}