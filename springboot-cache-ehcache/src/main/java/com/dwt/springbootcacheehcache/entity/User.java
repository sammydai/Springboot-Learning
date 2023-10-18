package com.dwt.springbootcacheehcache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Package: com.dwt.springbootcacheehcache.entity
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 19:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = 3250183889549295128L;

	private Long id;

	private String name;
}
