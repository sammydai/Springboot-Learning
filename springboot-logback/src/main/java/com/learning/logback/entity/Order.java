// 订单实体
package com.learning.logback.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal actualAmount;
    private String status; // PENDING/PROCESSING/SUCCESS/FAILED
    private Date createTime;
    private Date updateTime;
}

