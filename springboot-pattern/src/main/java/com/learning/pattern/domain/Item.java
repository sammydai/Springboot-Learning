package com.learning.pattern.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Package: com.learning.pattern.domain
 * @Description: Item
 * @Author: Sammy
 * @Date: 2020/12/18 14:22
 */
@Data
public class Item {

	//商品ID
	private long id;

	//商品数量
	private int quantity;

	// 商品单价
	private BigDecimal price;

	// 商品优惠
	private BigDecimal couponPrice;

	//商品运费
	private BigDecimal deliveryPrice;
}
