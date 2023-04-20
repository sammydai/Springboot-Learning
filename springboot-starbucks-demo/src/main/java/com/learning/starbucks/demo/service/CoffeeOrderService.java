package com.learning.starbucks.demo.service;

import com.learning.starbucks.demo.model.Coffee;
import com.learning.starbucks.demo.model.CoffeeOrder;
import com.learning.starbucks.demo.model.OrderState;
import com.learning.starbucks.demo.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * @Package: com.learning.starbucks.demo.service
 * @Description: Coffee Order Service
 * @Author: Sammy
 * @Date: 2020/12/6 09:42
 */
@Slf4j
@Service
@Transactional
public class CoffeeOrderService {

	@Autowired
	private CoffeeOrderRepository coffeeOrderRepository;

	public CoffeeOrder createOrder(String customer, Coffee... coffee) {
		CoffeeOrder coffeeOrder = CoffeeOrder.builder()
				.customer(customer)
				.items(Arrays.asList(coffee))
				.state(OrderState.INIT)
				.build();
		CoffeeOrder saved = coffeeOrderRepository.save(coffeeOrder);
		log.info("New Order: {}", saved);
		return saved;
	}

	public boolean updateState(CoffeeOrder order, OrderState state) {
		if (state.compareTo(order.getState())<=0) {
			log.warn("Wrong State order: {}, {}", state, order.getState());
			return false;
		}
		order.setState(state);
		coffeeOrderRepository.save(order);
		log.info("Updated Order: {}",order);
		return true;
	}
}
