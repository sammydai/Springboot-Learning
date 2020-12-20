package com.learning.pattern.templatemethod;

import com.learning.pattern.domain.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Package: com.learning.pattern.templatemethod
 * @Description: InternalUserCart
 * @Author: Sammy
 * @Date: 2020/12/18 14:42
 */
@Service(value = "InternalUserCart")
public class InternalUserCart extends AbstractCart{
	@Override
	protected void processCouponPrice(long userId, Item item) {
		item.setCouponPrice(BigDecimal.ZERO);
	}

	@Override
	protected void processDeliveryPrice(long userId, Item item) {
		item.setDeliveryPrice(BigDecimal.ZERO);
	}
}
