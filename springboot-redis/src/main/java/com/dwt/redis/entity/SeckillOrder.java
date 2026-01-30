package com.dwt.redis.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private Integer status;  // 0:待支付 1:已支付 2:已取消
    private Date createTime;
    private Date payTime;
}