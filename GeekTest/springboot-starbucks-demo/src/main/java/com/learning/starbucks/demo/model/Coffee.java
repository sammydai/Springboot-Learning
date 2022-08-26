package com.learning.starbucks.demo.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Package: com.learning.starbucks.demo.model
 * @Description: Coffee
 * @Author: Sammy
 * @Date: 2020/12/5 16:25
 */

@Entity
@Table(name = "T_COFFEE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Coffee extends BaseEntity implements Serializable{

	private String name;

	@Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
	private Money price;
}
