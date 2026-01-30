package com.dwt.redis.mapper;

import com.dwt.redis.entity.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SeckillMapper {
    
    // 创建订单
    int createOrder(SeckillOrder order);
    
    // 根据ID查询订单
    SeckillOrder getOrderById(Long id);
    
    // 更新订单状态
    int updateOrderStatus(@Param("id") Long id, @Param("status") Integer status);
    
    // 检查用户是否已购买
    int checkUserPurchased(@Param("userId") Long userId, @Param("productId") Long productId);
}