package com.learning.security.coupon;

import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.learning.security.coupon
 * @Description: CouponBatch
 * @Author: Sammy
 * @Date: 2021/1/18 09:53
 */
@Data
public class CouponBatch {
	private long id;
	private AtomicInteger totalCount;
	private AtomicInteger remainCount;
	private BigDecimal amount;
	private String reason;
}
