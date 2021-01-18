package com.learning.security.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Package: com.learning.security.coupon
 * @Description: Coupon
 * @Author: Sammy
 * @Date: 2021/1/18 09:33
 */
@Data
@AllArgsConstructor
public class Coupon {
	private long userId;
	private BigDecimal amount;
}
