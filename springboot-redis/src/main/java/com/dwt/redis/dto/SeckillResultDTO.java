package com.dwt.redis.dto;

import lombok.Data;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/2/2 10:06]
 */
@Data
public class SeckillResultDTO {
	private Long orderId;
	private String status;
	private String message;
	private Long userId;
	private Long productId;

	public static SeckillResultDTO success(Long orderId, Long userId, Long productId) {
		SeckillResultDTO dto = new SeckillResultDTO();
		dto.setOrderId(orderId);
		dto.setStatus("SUCCESS");
		dto.setMessage("秒杀成功");
		dto.setUserId(userId);
		dto.setProductId(productId);
		return dto;
	}

	public static SeckillResultDTO fail(String message) {
		SeckillResultDTO dto = new SeckillResultDTO();
		dto.setStatus("FAIL");
		dto.setMessage(message);
		return dto;
	}

	public static SeckillResultDTO waiting() {
		SeckillResultDTO dto = new SeckillResultDTO();
		dto.setStatus("WAITING");
		dto.setMessage("排队中");
		return dto;
	}
}
