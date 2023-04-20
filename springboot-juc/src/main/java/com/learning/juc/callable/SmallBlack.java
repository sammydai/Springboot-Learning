package com.learning.juc.callable;

/**
 * @Package: com.learning.juc.callable
 * @Description:
 * @Author: Sammy
 * @Date: 2022/9/6 16:21
 */

//我是小黑
public class SmallBlack {

	//取快递，同样需要小白的引用.
	public void getExpress(CallBack callBack) {
		System.out.println("我是小黑，我去取快递了！");
		System.out.println("取到快递，通知小白！");
		//回调函数，调用小白的call方法。
		callBack.call("快递已经帮你取到了！");
	}
}

