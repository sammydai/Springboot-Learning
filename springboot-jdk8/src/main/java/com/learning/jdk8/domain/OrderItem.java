package com.learning.jdk8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.learning.jdk8.domain
 * @Description: OrderItem
 * @Author: Sammy
 * @Date: 2020/12/30 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	private Long productId;//商品ID

	private String productName;//商品名称

	private Double productPrice;//商品价格

	private Integer productQuantity;//商品数量
}
