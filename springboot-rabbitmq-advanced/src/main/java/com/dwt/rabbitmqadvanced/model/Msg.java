package com.dwt.rabbitmqadvanced.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.dwt.rabbitmqadvanced.model
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 12:58
 */

@Data
public class Msg implements Serializable {

	private static final long serialVersionUID = -2404375645721712224L;

	private int id;

	private String content;

	private long ttl;

}
