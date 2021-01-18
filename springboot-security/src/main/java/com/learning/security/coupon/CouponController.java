package com.learning.security.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.IntStream;

/**
 * @Package: com.learning.security.coupon
 * @Description: CouponController
 * @Author: Sammy
 * @Date: 2021/1/18 09:54
 */
@RestController
public class CouponController {

	@GetMapping("wrong")
	public int wrong() {
		CouponCenter couponCenter = new CouponCenter();
		IntStream.rangeClosed(1,10000).forEach(i->{
			Coupon coupon = couponCenter.generateCouponWrong(1L, new BigDecimal("100"));
			couponCenter.sendCoupon(coupon);
		});
		return couponCenter.getTotalSent();
	}

	@GetMapping("right")
	public int right() {
		CouponCenter couponCenter = new CouponCenter();
		CouponBatch couponBatch = couponCenter.generateCouponBatch();
		IntStream.rangeClosed(1,10000).forEach(i->{
			Coupon coupon = couponCenter.generateCouponRight(1L, couponBatch);
			couponCenter.sendCoupon(coupon);
		});
		return couponCenter.getTotalSent();
	}
}
