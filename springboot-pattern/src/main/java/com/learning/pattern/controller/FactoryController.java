package com.learning.pattern.controller;

import com.learning.pattern.domain.Cart;
import com.learning.pattern.templatemethod.AbstractCart;
import com.learning.pattern.templatemethod.InternalUserCart;
import com.learning.pattern.templatemethod.NormalUserCart;
import com.learning.pattern.templatemethod.VipUserCart;
import com.learning.pattern.util.Db;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.learning.pattern.controller
 * @Description: Factory Pattern Controller
 * @Author: Sammy
 * @Date: 2020/12/18 14:43
 */
@Slf4j
@RestController
public class FactoryController {

	private static Map<Long, Integer> items = new HashMap<>();

    static {
        items.put(1L, 2);
        items.put(2L, 4);
    }


	@Autowired
	private ApplicationContext applicationContext;

	@GetMapping("wrongcart")
	public Cart wrongcart(@RequestParam("userId") int userId) {
		//根据用户ID获得用户类型
		String userCategory = Db.getUserCategory(userId);
		//普通用户处理逻辑
		if (userCategory.equals("Normal")) {
			NormalUserCart normalUserCart = new NormalUserCart();
			return normalUserCart.process(userId, items);
		}
		//VIP用户处理逻辑
		if (userCategory.equals("Vip")) {
			VipUserCart vipUserCart = new VipUserCart();
			return vipUserCart.process(userId, items);
		}
		//内部用户处理逻辑
		if (userCategory.equals("Internal")) {
			InternalUserCart internalUserCart = new InternalUserCart();
			return internalUserCart.process(userId, items);
		}
		return null;
	}

	/**
	 * 工厂模式 + 模板方法模式，不仅消除了重复代码，还避免了修改既有代码的风险
	 * @param userId
	 * @return
	 */
	@GetMapping("rightcart")
	public Cart right(@RequestParam("userId") long userId) {
		String userCategory = Db.getUserCategory(userId);
		AbstractCart cart = (AbstractCart) applicationContext.getBean(userCategory + "UserCart");
		return cart.process(userId, items);
	}
}
