package com.learning.starbucks.demo.service;

import com.learning.starbucks.demo.model.Coffee;
import com.learning.starbucks.demo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @Package: com.learning.starbucks.demo.service
 * @Description: Coffee Service
 * @Author: Sammy
 * @Date: 2020/12/6 09:36
 */
@Service
@Slf4j
public class CoffeeService {

	@Autowired
	private CoffeeRepository coffeeRepository;

	public List<Coffee> findAllCoffee(){
		return coffeeRepository.findAll();
	}

	public Optional<Coffee> findOneCoffee(String name) {
		ExampleMatcher matcher = ExampleMatcher.matching().
				withMatcher("name", exact().ignoreCase());
		Optional<Coffee> coffee = coffeeRepository.findOne(
				Example.of(Coffee.builder().name(name).build(), matcher));
		log.info("Coffee Found: {}",coffee);
		return coffee;
	}
}
