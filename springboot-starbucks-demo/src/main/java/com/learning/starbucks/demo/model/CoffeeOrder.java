package com.learning.starbucks.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.learning.starbucks.demo.model
 * @Description: Coffee Order
 * @Author: Sammy
 * @Date: 2020/12/6 09:28
 */
@Entity
@Table(name = "T_ORDER")
@Data
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeOrder extends BaseEntity implements Serializable {
	private String customer;

	@ManyToMany
	@JoinTable(name = "T_ORDER_COFFEE")
	@OrderBy("id")
	private List<Coffee> items;

	@Enumerated
	@Column(nullable = false)
	private OrderState state;

}
