package com.dwt.springbootjpa;

import com.dwt.springbootjpa.model.Coffee;
import com.dwt.springbootjpa.model.CoffeeOrder;
import com.dwt.springbootjpa.model.OrderState;
import com.dwt.springbootjpa.repository.CoffeeOrderRepository;
import com.dwt.springbootjpa.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private CoffeeRepository coffeeRepository;

	@Autowired
	private CoffeeOrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		initOrders();
		findOrders();
	}

	private void initOrders() {

		Coffee latte = Coffee.builder().name("lattee")
				.price(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.build();
		coffeeRepository.save(latte);
		log.info("=============>Coffee:{}",latte);

		Coffee espresso = Coffee.builder().name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.build();
		coffeeRepository.save(espresso);
		log.info("=============>Coffee: {}", espresso);

		CoffeeOrder order = CoffeeOrder.builder().customer("Li Lei")
				.items(Collections.singletonList(espresso))
				.state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order: {}", order);

		order = CoffeeOrder.builder()
				.customer("Li Lei")
				.items(Arrays.asList(espresso, latte))
				.state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order: {}", order);
	}

	private void findOrders() {
		coffeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
				.forEach(c -> log.info("===========>Loading:{}", c));

		List<CoffeeOrder> list = orderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
		log.info("=====>findTop3ByOrderByUpdateTimeDescIdAsc:{}",getJoinedOrderId(list));

		list = orderRepository.findByCustomerOrderById("Li Lei");
		log.info("=====>findByCustomerOrderById: {}", getJoinedOrderId(list));

		list.forEach(o -> {
			log.info("Order {}", o.getId());
			o.getItems().forEach(i -> log.info("  Item {}", i));
		});

		list = orderRepository.findByItems_Name("latte");
		log.info("findByItems_Name: {}", getJoinedOrderId(list));

	}

		private String getJoinedOrderId(List<CoffeeOrder> list) {
		return list.stream().map(o -> o.getId().toString())
				.collect(Collectors.joining(","));
	}
}
