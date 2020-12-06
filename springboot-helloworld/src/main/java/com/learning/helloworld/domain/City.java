package com.learning.helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class City {
	/**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市code
     */
    private Integer cityCode;
}
