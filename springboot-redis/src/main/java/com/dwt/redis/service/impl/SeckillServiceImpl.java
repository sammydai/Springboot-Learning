package com.dwt.redis.service.impl;


import com.dwt.redis.dto.SeckillOrderDTO;
import com.dwt.redis.entity.SeckillOrder;
import com.dwt.redis.mapper.SeckillMapper;
import com.dwt.redis.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    /**
     * 我们还需要解释，在 seckill 方法中，数据库操作（createOrder）是唯一受事务管理的。
     * 其他 Redis 操作不属于事务，它们要么在事务之前执行（如库存预减），
     * 要么在事务之后（如记录购买），但整个方法执行过程中，如果任何地方抛异常，数据库事务会回滚。
     * 但 Redis 的更改需要手动补偿，就像 catch 块中已经做的那样。
     *
     * 同时，用户可能关心：如果启用 @Transactional，那么当 Redis 操作失败时，数据库插入的订单会被回滚吗？
     * 答案是：会，因为方法抛出异常，Spring 事务拦截器会检测到并回滚事务。
     * 但 Redis 本身已经执行的更改（如 decrement、set）不会自动撤销，
     * 需要手动补偿（如你在 catch 块中已经做了库存回滚）。
     * @param userId 用户ID
     * @param productId 商品ID
     * @return
     */
    @Override
    // @Transactional(rollbackFor = Exception.class)
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
            String tokenKey = ORDER_TOKEN_KEY + seckillOrder.getId();
            redisTemplate.opsForValue().set(tokenKey,token,30,TimeUnit.MINUTES);
            // log.info("秒杀成功，订单ID: {}", seckillOrder.getId());
            return seckillOrder.getId();
        } catch (Exception e) {
            redisTemplate.opsForValue().increment(STOCK_KEY + productId);
            throw new RuntimeException("秒杀失败", e);
        }
    }

    private boolean checkUserPurChased(Long userId, Long productId) {
        String key = USER_PURCHASED_KEY + productId + ":" + userId;
        return redisTemplate.hasKey(key);
    }

    @Override
    public String checkResult(Long orderId) {
        SeckillOrder order = seckillMapper.getOrderById(orderId);
        if (order==null) {
            return "订单不存在";
        }
        switch (order.getStatus()){
            case 0:
                return "待支付";
            case 1:
                return "已支付";
            case 2:
                return "已取消";
            default:
                return "未知订单";
        }

    }

    @Override
    public SeckillOrderDTO getOrderDetail(Long orderId) {
        // 1. 从数据库查询订单信息
        SeckillOrder order = seckillMapper.getOrderById(orderId);
        if (order == null) {
            return null;
        }

        // 2. 创建并填充DTO对象
        SeckillOrderDTO dto = new SeckillOrderDTO();

        // 设置基本信息
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setProductId(order.getProductId());
        dto.setQuantity(order.getQuantity());
        dto.setPrice(order.getPrice());
        dto.setStatus(order.getStatus());
        dto.setCreateTime(order.getCreateTime());
        dto.setPayTime(order.getPayTime());

        // 设置状态描述
        String statusDesc = getStatusDesc(order.getStatus());
        dto.setStatusDesc(statusDesc);

        // 设置商品名称（这里使用固定格式）
        dto.setProductName("秒杀商品-" + order.getProductId());

        // 计算总金额
        if (order.getPrice() != null && order.getQuantity() != null) {
            BigDecimal totalAmount = order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            dto.setTotalAmount(totalAmount);
        } else {
            dto.setTotalAmount(BigDecimal.ZERO);
        }

        // 设置订单号（如果有）
        if (order.getOrderNo() != null && !order.getOrderNo().isEmpty()) {
            dto.setOrderNo(order.getOrderNo());
        } else {
            // 如果没有订单号，生成一个简单的
            dto.setOrderNo("SK" + order.getId() + order.getUserId());
        }

        return dto;
    }

    /**
     * 根据状态码获取状态描述
     */
    private String getStatusDesc(Integer status) {
        if (status == null) {
            return "未知状态";
        }

        switch (status) {
            case 0: return "待支付";
            case 1: return "已支付";
            case 2: return "已取消";
            case 3: return "已发货";
            case 4: return "已完成";
            case 5: return "已退款";
            default: return "未知状态";
        }
    }

    @Override
    public void initStock(Long productId, Integer stock) {
        String key = STOCK_KEY + productId;
        redisTemplate.opsForValue().set(key, stock);
    }


}