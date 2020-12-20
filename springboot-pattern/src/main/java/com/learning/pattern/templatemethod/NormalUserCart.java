package com.learning.pattern.templatemethod;

import com.learning.pattern.domain.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Package: com.learning.pattern.templatemethod
 * @Description: NormalUserCart
 * @Author: Sammy
 * @Date: 2020/12/18 14:25
 */

//普通用户购物车处理
@Service(value = "NormalUserCart")
public class NormalUserCart extends AbstractCart {

	@Override
	protected void processCouponPrice(long userId, Item item) {
		item.setCouponPrice(BigDecimal.ZERO);
	}

	@Override
	protected void processDeliveryPrice(long userId, Item item) {
		item.setDeliveryPrice(item.getDeliveryPrice()
		.multiply(BigDecimal.valueOf(item.getQuantity()))
		.multiply(new BigDecimal("0.1")));
	}
}

