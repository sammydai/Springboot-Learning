package com.dwt.redis.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillOrderDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private Integer status;
    private String statusDesc;
    private Date createTime;
    private Date payTime;
    private String orderNo;
}