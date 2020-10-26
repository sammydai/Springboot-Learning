package com.dwt;

import java.util.concurrent.Callable;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/21 21:59
 */

public class FutureTest implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
	}
}
