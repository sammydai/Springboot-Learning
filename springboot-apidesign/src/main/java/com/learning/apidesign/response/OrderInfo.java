package com.learning.apidesign.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.learning.apidesign.response
 * @Description: OrderInfo
 * @Author: Sammy
 * @Date: 2020/12/21 20:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
	private String status;
    private long orderId;
}
