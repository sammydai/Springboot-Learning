package com.learning.helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Package: com.learning.helloworld.domain
 * @Description: People
 * @Author: Sammy
 * @Date: 2020/12/6 13:05
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class People {
	/**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     *  城市信息
     */
    private City city;
}
