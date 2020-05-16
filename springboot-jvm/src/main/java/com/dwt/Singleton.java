package com.dwt;

import java.util.Date;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/5 19:46
 */

public class Singleton {

	private static Singleton INSTANCE;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (INSTANCE==null) {
			synchronized (Singleton.class) {
				INSTANCE = new Singleton();
			}
		}
		return INSTANCE;
	}
}
