package com.learning.starbucks.demo.repository;

import com.learning.starbucks.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Package: com.learning.starbucks.demo.repository
 * @Description: CoffeeRepository
 * @Author: Sammy
 * @Date: 2020/12/6 09:35
 */

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}
