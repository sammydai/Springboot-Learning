package com.dwt.redis.service;


import com.dwt.redis.RedisApplication;
import com.dwt.redis.dto.SeckillOrderDTO;
import com.dwt.redis.entity.SeckillOrder;
import com.dwt.redis.mapper.SeckillMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;
    
    @Autowired
    private SeckillMapper seckillMapper;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private static final Long TEST_PRODUCT_ID = 1001L;
    private static final Long TEST_USER_ID = 1001L;
    
    @BeforeEach
    void setUp() {
        // 初始化商品库存
        log.info("初始化商品库存");
        redisTemplate.delete("seckill:stock:" + TEST_PRODUCT_ID);
        redisTemplate.delete("seckill:purchased:" + TEST_PRODUCT_ID + ":" + TEST_USER_ID);
    }
    
    // ==================== getOrderDetail 测试 ====================
    
    @Test
    void testGetOrderDetail_不同订单状态() {
        log.info("\n=== 测试不同订单状态 ===");
        
        // 测试待支付状态
        SeckillOrder pendingOrder = createTestOrder(TEST_USER_ID, TEST_PRODUCT_ID, 0);
        SeckillOrderDTO pendingResult = seckillService.getOrderDetail(pendingOrder.getId());
        assertEquals("待支付", pendingResult.getStatusDesc());
        log.info("待支付状态测试通过");
        
        // 测试已支付状态
        SeckillOrder paidOrder = createTestOrder(TEST_USER_ID, TEST_PRODUCT_ID + 1, 1);
        paidOrder.setPayTime(new Date());
        seckillMapper.updateOrderStatus(paidOrder.getId(), 1);
        SeckillOrderDTO paidResult = seckillService.getOrderDetail(paidOrder.getId());
        assertEquals("已支付", paidResult.getStatusDesc());
        log.info("已支付状态测试通过");
        
        // 测试已取消状态
        SeckillOrder cancelledOrder = createTestOrder(TEST_USER_ID, TEST_PRODUCT_ID + 2, 2);
        SeckillOrderDTO cancelledResult = seckillService.getOrderDetail(cancelledOrder.getId());
        assertEquals("已取消", cancelledResult.getStatusDesc());
        log.info("已取消状态测试通过");
    }
    
    @Test
    void testGetOrderDetail_金额计算正确性() {
        log.info("\n=== 测试金额计算正确性 ===");
        
        // 测试单件商品
        SeckillOrder singleItem = createTestOrder(TEST_USER_ID, TEST_PRODUCT_ID, 0);
        singleItem.setQuantity(1);
        singleItem.setPrice(new BigDecimal("99.99"));
        seckillMapper.updateOrderStatus(singleItem.getId(), 0);
        
        SeckillOrderDTO singleResult = seckillService.getOrderDetail(singleItem.getId());
        assertEquals(new BigDecimal("99.99"), singleResult.getTotalAmount());
        log.info("单件商品金额计算正确: " + singleResult.getTotalAmount());
        
        // 测试多件商品
        SeckillOrder multiItem = createTestOrder(TEST_USER_ID + 1, TEST_PRODUCT_ID, 0);
        multiItem.setQuantity(3);
        multiItem.setPrice(new BigDecimal("49.99"));
        seckillMapper.updateOrderStatus(multiItem.getId(), 0);
        
        SeckillOrderDTO multiResult = seckillService.getOrderDetail(multiItem.getId());
        assertEquals(new BigDecimal("149.97"), multiResult.getTotalAmount());
        log.info("多件商品金额计算正确: " + multiResult.getTotalAmount());
    }
    
    @Test
    void testGetOrderDetail_空值和边界情况() {
        log.info("\n=== 测试空值和边界情况 ===");
        
        // 测试数量为0的情况（虽然业务上不应该有）
        SeckillOrder zeroQuantity = createTestOrder(TEST_USER_ID, TEST_PRODUCT_ID, 0);
        zeroQuantity.setQuantity(0);
        seckillMapper.updateOrderStatus(zeroQuantity.getId(), 0);
        
        SeckillOrderDTO zeroResult = seckillService.getOrderDetail(zeroQuantity.getId());
        assertEquals(BigDecimal.ZERO, zeroResult.getTotalAmount());
        log.info("数量为0时总金额为0: " + zeroResult.getTotalAmount());
        
        // 测试价格为null的情况
        SeckillOrder nullPrice = createTestOrder(TEST_USER_ID + 1, TEST_PRODUCT_ID, 0);
        nullPrice.setPrice(null);
        seckillMapper.updateOrderStatus(nullPrice.getId(), 0);
        
        SeckillOrderDTO nullPriceResult = seckillService.getOrderDetail(nullPrice.getId());
        assertEquals(BigDecimal.ZERO, nullPriceResult.getTotalAmount());
        log.info("价格为null时总金额为0: " + nullPriceResult.getTotalAmount());
    }
    
    // ==================== seckill 方法测试 ====================
    
    @Test
    void testSeckill_正常秒杀() {
        log.info("\n=== 测试正常秒杀 ===");
        
        // 初始化库存
        seckillService.initStock(TEST_PRODUCT_ID, 10);
        
        // 第一次秒杀应该成功
        Long orderId = seckillService.seckill(TEST_USER_ID, TEST_PRODUCT_ID);
        assertNotNull(orderId);
        assertTrue(orderId > 0);
        
        // 验证订单详情
        SeckillOrderDTO orderDetail = seckillService.getOrderDetail(orderId);
        assertNotNull(orderDetail);
        assertEquals(TEST_USER_ID, orderDetail.getUserId());
        assertEquals(TEST_PRODUCT_ID, orderDetail.getProductId());
        assertEquals("待支付", orderDetail.getStatusDesc());
        
        log.info("正常秒杀测试通过，订单ID: " + orderId);
    }
    
    @Test
    void testSeckill_重复秒杀() {
        log.info("\n=== 测试重复秒杀 ===");
        
        // 初始化库存
        seckillService.initStock(TEST_PRODUCT_ID, 10);
        
        // 第一次秒杀
        Long firstOrderId = seckillService.seckill(TEST_USER_ID, TEST_PRODUCT_ID);
        assertNotNull(firstOrderId);
        
        // 第二次秒杀应该失败
        try {
            seckillService.seckill(TEST_USER_ID, TEST_PRODUCT_ID);
            fail("重复秒杀应该抛出异常");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("已经购买过"));
            log.info("重复秒杀测试通过，错误信息: " + e.getMessage());
        }
    }
    
    @Test
    void testSeckill_库存不足() {
        log.info("\n=== 测试库存不足 ===");
        
        // 初始化库存为1
        seckillService.initStock(TEST_PRODUCT_ID, 1);
        
        // 第一次秒杀应该成功
        Long firstOrderId = seckillService.seckill(TEST_USER_ID, TEST_PRODUCT_ID);
        assertNotNull(firstOrderId);
        
        // 第二次秒杀应该失败
        try {
            seckillService.seckill(TEST_USER_ID + 1, TEST_PRODUCT_ID);
            fail("库存不足应该抛出异常");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("已售罄"));
            log.info("库存不足测试通过，错误信息: " + e.getMessage());
        }
    }
    
    // ==================== checkResult 方法测试 ====================
    
    @Test
    void testCheckResult_各种状态() {
        log.info("\n=== 测试checkResult各种状态 ===");
        
        // 创建各种状态的订单
        SeckillOrder order1 = createTestOrder(TEST_USER_ID, TEST_PRODUCT_ID, 0);
        assertEquals("待支付", seckillService.checkResult(order1.getId()));
        
        SeckillOrder order2 = createTestOrder(TEST_USER_ID + 1, TEST_PRODUCT_ID, 1);
        assertEquals("已支付", seckillService.checkResult(order2.getId()));
        
        SeckillOrder order3 = createTestOrder(TEST_USER_ID + 2, TEST_PRODUCT_ID, 2);
        assertEquals("已取消", seckillService.checkResult(order3.getId()));
        
        // 测试未知状态
        SeckillOrder order4 = createTestOrder(TEST_USER_ID + 3, TEST_PRODUCT_ID, 99);
        assertEquals("未知状态", seckillService.checkResult(order4.getId()));
        
        // 测试订单不存在
        assertEquals("订单不存在", seckillService.checkResult(999999L));
        
        log.info("checkResult所有状态测试通过");
    }
    
    // ==================== 辅助方法 ====================
    
    private SeckillOrder createTestOrder(Long userId, Long productId, Integer status) {
        SeckillOrder order = new SeckillOrder();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(2);
        order.setPrice(new BigDecimal("99.99"));
        order.setStatus(status);
        order.setCreateTime(new Date());
        
        seckillMapper.createOrder(order);
        return order;
    }
}