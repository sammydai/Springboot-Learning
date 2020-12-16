package com.learning.helloworld.reflection;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: org.geekbang.time.commonmistakes.advancedfeatures.reflectionissue
 * @Description: Parent <T>
 * @Author: Sammy
 * @Date: 2020/12/16 15:36
 */
public class GenericsIssue {
    public static void main(String[] args) {
        Child1 child1 = new Child1();
        Arrays.stream(child1.getClass().getMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child1,"test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(child1.toString());
    }
}

class Parent<T> {
    //用于记录value更新的次数，模拟日志记录的逻辑
    AtomicInteger updateCount = new AtomicInteger();
    private T value;
    //重写toString，输出值和值更新次数
    @Override
    public String toString() {
		return String.format("value: %s updateCount: %d", value, updateCount.get());
    }
    //设置值
    public void setValue(T value) {
        System.out.println("Parent.setValue called");
        this.value = value;
        updateCount.incrementAndGet();
    }
}

/**
 * 子类没有指定 String 泛型参数，
 * 父类的泛型方法 setValue(T value)
 * 在泛型擦除后是 setValue(Object value)
 */
class Child1 extends Parent {
    public void setValue(String value) {
        System.out.println("Child1.setValue called");
        super.setValue(value);
    }
}

