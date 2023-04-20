package com.learning.juc.callable;

/**
 * @Package: com.learning.juc.callable
 * @Description:
 * @Author: Sammy
 * @Date: 2022/9/6 16:12
 */

//员工
public class Emp {
	//老板雇我，就是为了让我替他工作，所以work方法中的形参为老板的引用。
	public void work(CallBack callBack) {
		System.out.println("我接手了老板的这个工作！");
		System.out.println("正在工作中！");
		System.out.println("工作做完了！");
		//工作做完之后呢，需要通知老板，也就是回调
		callBack.call("老板，您吩咐的工作我完成了！");
	}

	public static void main(String[] args) {
		Boss boss = new Boss();
		//把工作交给员工
		boss.doSomething();
		System.out.println();
		SmallWhite smallWhite = new SmallWhite();
		//叫小黑帮忙去快递
		smallWhite.help();

	}

}


