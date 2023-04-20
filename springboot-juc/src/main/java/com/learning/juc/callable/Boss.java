package com.learning.juc.callable;

/**
 * @Package: com.learning.juc.callable
 * @Description:
 * @Author: Sammy
 * @Date: 2022/9/6 16:11
 */

//老板
public class Boss implements CallBack {
	//老板持有员工对象
	private final Emp emp = new Emp();

	public void play() {

		System.out.println("我是老板，我去玩了，事情交给你们干了！");
	}

	//本来是老板的一部分工作，老板不想做了，交给员工来干。
	public void doSomething() {
		//因为老板把工作交给你了，自己当然要出去干其他事，所以开启异步。
		new Thread(new Runnable() {
			public void run() {
				//传入当前老板的引用，这样才能帮老板做他的事。
				emp.work(Boss.this);
			}
		}).start();
		//老板出去玩了，事情已经交代员工了
		this.play();
	}

	//回调函数，做完老板吩咐的事情后，通知老板。（也就是响应）
	public void call(String info) {
		System.out.println(info);
	}
}


