package com.dwt.redis.service;

import com.dwt.redis.dto.SeckillOrderDTO;

public interface SeckillService {
    /**
     * 秒杀下单
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 订单ID
     */
    Long seckill(Long userId, Long productId);
    
    /**
     * 检查秒杀结果
     * @param orderId 订单ID
     * @return 秒杀结果
     */
    String checkResult(Long orderId);

    SeckillOrderDTO getOrderDetail(Long orderId);

    void initStock(Long productId, Integer stock);

}