package com.dwt.redis.controller;

import com.dwt.redis.dto.Result;
import com.dwt.redis.dto.SeckillOrderDTO;
import com.dwt.redis.dto.SeckillResultDTO;
import com.dwt.redis.entity.SeckillOrder;
import com.dwt.redis.enums.ResultCode;
import com.dwt.redis.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/2/2 09:53]
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

	@Autowired
	private SeckillService seckillService;

	@PostMapping("/order")
	public Result<SeckillResultDTO> seckill(@RequestParam Long userId, @RequestParam Long productId) {
		try {
			Long orderId = seckillService.seckill(userId, productId);
			SeckillResultDTO resultDTO = SeckillResultDTO.success(orderId, userId, productId);
			return Result.success(ResultCode.SECKILL_SUCCESS.getMessage(), resultDTO);
		} catch (RuntimeException e) {
			SeckillResultDTO failDTO = SeckillResultDTO.fail(e.getMessage());

			// 根据不同的异常返回不同的错误码
			if (e.getMessage().contains("已经购买过")) {
				return Result.error(ResultCode.SECKILL_REPEAT.getCode(),
						ResultCode.SECKILL_REPEAT.getMessage(), failDTO);
			} else if (e.getMessage().contains("已售罄")) {
				return Result.error(ResultCode.STOCK_NOT_ENOUGH.getCode(),
						ResultCode.STOCK_NOT_ENOUGH.getMessage(), failDTO);
			} else {
				return Result.error(ResultCode.BUSINESS_ERROR.getCode(),
						e.getMessage(), failDTO);
			}
		} catch (Exception e) {
			SeckillResultDTO failDTO = SeckillResultDTO.fail("系统异常");
			return Result.error(ResultCode.SYSTEM_ERROR.getCode(),
					ResultCode.SYSTEM_ERROR.getMessage(), failDTO);
		}
	}

	/**
	 * 根据订单ID获取秒杀结果。
	 *
	 * @param orderId 订单ID，用于查询对应的秒杀结果
	 * @return 返回封装了秒杀结果的Result对象，成功时包含状态信息，失败时包含错误码和错误信息
	 */
	@GetMapping("/result/{orderId}")
	public Result<String> getResult(@PathVariable Long orderId) {
		// 尝试调用服务层方法检查秒杀结果
		try {
			String status = seckillService.checkResult(orderId);
			return Result.success(status);
		} catch (Exception e) {
			// 捕获异常并返回业务错误信息
			return Result.error(ResultCode.BUSINESS_ERROR.getCode(), e.getMessage());
		}
	}

	@GetMapping("/order/{orderId}")
	public Result<SeckillOrderDTO> getOrderDetail(@PathVariable Long orderId) {
		try{
			SeckillOrderDTO orderDTO = seckillService.getOrderDetail(orderId);
			if (orderDTO==null) {
				return Result.error(ResultCode.ORDER_NOT_EXIST.getCode(), ResultCode.ORDER_NOT_EXIST.getMessage());
			}
			return Result.success(orderDTO);
		}catch (Exception e){
			return Result.error(ResultCode.SYSTEM_ERROR.getCode(),
					ResultCode.SYSTEM_ERROR.getMessage());
		}
	}

	/**
	 * 初始化库存（管理员使用）
	 */
	@PostMapping("/initStock")
	public Result<String> initStock(@RequestParam Long productId,
									@RequestParam Integer stock) {
		try {
			seckillService.initStock(productId, stock);
			return Result.success("库存初始化成功");
		} catch (Exception e) {
			return Result.error(ResultCode.BUSINESS_ERROR.getCode(),
					e.getMessage());
		}
	}


}
