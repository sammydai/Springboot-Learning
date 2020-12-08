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

}
