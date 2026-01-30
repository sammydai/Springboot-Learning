// 订单历史实体
package com.learning.logback.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderHistory {
    private Long id;
    private Long orderId;
    private String orderNo;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal actualAmount;
    private String status;
    private Date syncTime;
}