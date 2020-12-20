package com.learning.pattern.templatemethod;

import com.learning.pattern.domain.Item;
import com.learning.pattern.util.Db;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Package: com.learning.pattern.templatemethod
 * @Description: VipUserCart
 * @Author: Sammy
 * @Date: 2020/12/18 14:37
 */
@Service(value = "VipUserCart")
public class VipUserCart extends NormalUserCart {
	@Override
	protected void processCouponPrice(long userId, Item item) {
		if (item.getQuantity() > 2) {
			item.setCouponPrice(item.getPrice()
					.multiply(BigDecimal.valueOf(100 - Db.getUserCouponPercent(userId))
							.divide(new BigDecimal("100")))
					.multiply(BigDecimal.valueOf(item.getQuantity()-2)));
		} else {
			item.setCouponPrice(BigDecimal.ZERO);
		}
	}
}
