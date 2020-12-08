package com.learning.helloworld.optional;

import com.learning.helloworld.domain.City;
import com.learning.helloworld.domain.People;

import java.util.Optional;

/**
 * @Package: com.learning.helloworld.optional
 * @Description: Optional Method Test
 * @Author: Sammy
 * @Date: 2020/12/8 10:23
 */

public class OptionalMethod {

	/**
	 * 这样才是正确使用Optional的姿势。
	 * 那么按照这种思路，我们可以安心的进行链式调用，而不是一层层判断了。
	 *
	 * @param city
	 * @return
	 */
	public static String getName(City city) {
		return Optional.ofNullable(city)
				.map(cc -> cc.getCityName())
				.orElse("Unknown");
	}

	/**
	 * 写法错误
	 *
	 * @param u
	 * @return
	 */
	public static String getNameWrong(City u) {
		Optional<City> city = Optional.ofNullable(u);
		if (!city.isPresent())
			return "Unknown";
		return city.get().getCityName();
	}

	public static String getCityNameWrong(People people) throws IllegalArgumentException {
		if (people != null) {
			City city = people.getCity();
			if (city != null) {
				return city.getCityName();
			}
		}
		throw new IllegalArgumentException("The value of param comp isn't available.");
	}

	public static String getCityNameRight(People people) throws IllegalArgumentException {
		return Optional.ofNullable(people)
				.map(pp -> pp.getCity())
				.map(cc -> cc.getCityName())
				.orElseThrow(()->new IllegalArgumentException("The value of param comp isn't available."));
	}


}
