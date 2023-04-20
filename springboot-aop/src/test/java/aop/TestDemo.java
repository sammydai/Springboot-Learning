package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Package: aop
 * @Description: Test
 * @Author: Sammy
 * @Date: 2022/8/26 22:11
 */

public class TestDemo {
	public static void main(String[] args) {
		AService as = new AServiceImpl();
		AService proxy = (AService) Proxy.newProxyInstance(as.getClass().getClassLoader(), as.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return method.invoke(as, args);
			}
		});

		BService bs = new BServiceImpl();

		((BServiceImpl) bs).setaService(as);
		((AServiceImpl) as).setbService(bs);
		proxy.a();
	}
}
