package com.learning.helloworld.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Package: com.learning.helloworld.domain
 * @Description: Town
 * @Author: Sammy
 * @Date: 2020/12/7 22:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Town extends City {

	private String townname;

	public Town(String cityName, Integer cityCode, String townname) {
		super(cityName, cityCode);
		this.townname = townname;
	}
}
