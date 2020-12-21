package com.learning.pattern.reflection;

import com.learning.pattern.annotation.BankAPI;
import com.learning.pattern.annotation.BankAPIField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Package: com.learning.pattern.reflection
 * @Description: PayAPI
 * @Author: Sammy
 * @Date: 2020/12/21 10:04
 */
@Data
@BankAPI(url = "/bank/pay", desc = "支付接口")
public class PayAPI extends AbstractAPI {

	@BankAPIField(order = 1, type = "N", length = 20)
	private long userId;

	@BankAPIField(order = 2, type = "M", length = 10)
	private BigDecimal amount;
}
