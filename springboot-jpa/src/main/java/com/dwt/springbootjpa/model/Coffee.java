package com.dwt.springbootjpa.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Package: com.dwt.springbootjpa.model
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/23 14:19
 */

@Entity
@Table(name = "T_MENU")
@Builder
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Coffee extends BaseEntity implements Serializable {
	private String name;
	@Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
			parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
	private Money price;

}
