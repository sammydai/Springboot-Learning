package com.learning.helloworld.nullvalue;

import lombok.Data;

import java.util.Optional;

/**
 * @Package: com.learning.helloworld.nullvalue
 * @Description: UserDto
 * @Author: Sammy
 * @Date: 2020/12/9 07:52
 */
@Data
public class UserDto {
	private Long id;
	private Optional<String> name;
	private Optional<Integer> age;
}
