package com.dwt.redis.service.impl;


import com.dwt.redis.entity.SeckillOrder;
import com.dwt.redis.mapper.SeckillMapper;
import com.dwt.redis.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {
    
    // 商品库存key前缀
    private static final String STOCK_KEY = "seckill:stock:";
    // 订单token key前缀
    private static final String ORDER_TOKEN_KEY = "seckill:token:";
    // 用户购买记录key前缀
    private static final String USER_PURCHASED_KEY = "seckill:purchased:";
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private SeckillMapper seckillMapper;


    @Override
    public Long seckill(Long userId, Long productId) {
        //1. 用户是否已经购买过该商品
        if(checkUserPurChased(userId,productId)){
            throw new RuntimeException("您已经购买过该商品");
        }
        //2. 预减库存
        Long stock = redisTemplate.opsForValue().decrement(STOCK_KEY + productId);
        if (stock == null || stock < 0) {
            redisTemplate.opsForValue().increment(STOCK_KEY + productId);
            throw new RuntimeException("该商品已售罄");
        }
        //3. 创建订单
        try {
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setUserId(userId);
            seckillOrder.setProductId(productId);
            seckillOrder.setQuantity(1);
            seckillOrder.setPrice(new BigDecimal("99.99"));
            seckillOrder.setStatus(0);
            seckillOrder.setCreateTime(new Date());
            seckillMapper.createOrder(seckillOrder);
            //4. 记录用户购买信息
            String userKey = USER_PURCHASED_KEY + productId + ":" + userId;
            redisTemplate.opsForValue().set(userKey,"1",24,TimeUnit.HOURS);
            //5. 生成订单token（防止重复提交）
            String token = UUID.randomUUID().toString();
            ORDER_TOKEN_KEY+seckillOrder.getId()
        } catch (Exception e) {

        }
    }

    private boolean checkUserPurChased(Long userId, Long productId) {
    }

    @Override
    public String checkResult(Long orderId) {
        return "";
    }
}