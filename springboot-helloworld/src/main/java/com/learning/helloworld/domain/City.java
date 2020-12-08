package com.learning.helloworld.domain;

import lombok.*;

import java.util.Objects;

/**
 * @Package: com.learning.helloworld.domain
 * @Description: City Test ConditionOnBean
 * @Author: Sammy
 * @Date: 2020/12/6 13:04
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {
	/**
     * 城市名称
	 * @EqualsAndHashCode.Exclude equals方法中排除此属性值
     */
	@EqualsAndHashCode.Exclude
    private String cityName;

    /**
     * 城市code
     */
    private Integer cityCode;

	// @Override
	// public boolean equals(Object o) {
	// 	if (this == o) return true;
	// 	if (o == null || getClass() != o.getClass()) return false;
	//
	// 	City city = (City) o;
	//
	// 	if (cityName != null ? !cityName.equals(city.cityName) : city.cityName != null) return false;
	// 	return cityCode != null ? cityCode.equals(city.cityCode) : city.cityCode == null;
	// }

	// @Override
	// public int hashCode() {
	// 	int result = cityName != null ? cityName.hashCode() : 0;
	// 	result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
	// 	return result;
	// }
}
