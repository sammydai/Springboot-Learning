package com.learning.juc.callable;

/**
 * @Package: com.learning.juc.callable
 * @Description: 回调其实就是，一些类（类A）实现了回调接口之后，它包含着另外一个类（类B）的引用，类B中含有一个类A作为形参的方法，
 * 调用该方法后就能获得到类A的引用，也就才能调用类A的回调方法。如果不理解的话，直接这么记即可。
 * @Author: Sammy
 * @Date: 2022/9/6 16:21
 */

//我是小白，同样也需要实现回调接口
public class SmallWhite implements CallBack {
	//我持有小黑的引用，因为我需要他的帮助
	private final SmallBlack smallBlack = new SmallBlack();

	//求助小黑
	public void help() {
		System.out.println("我是小白，我的快递到了，我在外边兼职，取不了，你帮我取吧！");
		//开启多线程，因为小白还要继续做他的兼职
		new Thread(new Runnable() {
			public void run() {
				//传入小白的引用
				smallBlack.getExpress(SmallWhite.this);
			}
		}).start();
		//事情吩咐完毕，小白继续干活
		work();
	}

	public void work() {
		System.out.println("继续兼职！");
	}

	public void call(String info) {
		System.out.println(info + "我知道了，谢谢你！");
	}
}


