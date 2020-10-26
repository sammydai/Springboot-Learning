package com.dwt;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/23 12:41
 */

/**
 *
 */
public class SynDemo implements Runnable{

	private static int count;

	public SynDemo() {
		count = 0;
	}

	public void countAdd() {
		synchronized (this){
			for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("线程名:"+Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
		}
	}

	/**
	 * 锁的是方法，和synchronized (this)相同
	 */
	public synchronized void countAddSync(){
		for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("线程名:"+Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
	}

	public static synchronized void countAddStaticSync(){
		for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("线程名:"+Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
	}

	public static int getCount() {
		return count;
	}

	 public void printCount() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void method(){
		synchronized (SynDemo.class){
			for (int i = 0; i < 5; i ++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		// countAdd();

		// countAddSync();

		// String name = Thread.currentThread().getName();
		// if ("mt1".equals(name)) {
		// 	countAdd();
		// }else if ("mt2".equals(name)){
		// 	printCount();
		// }

		// countAddStaticSync();

		method();
	}

	public static void main(String[] args) {
		//场景1： 锁的是同一个对象，synchronize（this），只允许一个线程执行
		// SynDemo synDemo = new SynDemo();
		// new Thread(synDemo,"thread-1").start();
		// new Thread(synDemo,"thread-2").start();

		//场景2： 锁的是不同对象，synchronize（this），锁的是对象，两个对象是两把锁，可以并行
		// SynDemo synDemo1 = new SynDemo();
		// SynDemo synDemo2 = new SynDemo();
		// new Thread(synDemo1,"thread-1").start();
		// new Thread(synDemo2,"thread-2").start();

		//场景3： 锁的是同一个对象，访问该对象的非synchronized代码块不收阻塞
		// SynDemo synDemo = new SynDemo();
		// new Thread(synDemo,"mt1").start();
		// new Thread(synDemo,"mt2").start();

		// 场景4：锁的是静态方法，所有类的对象都会收阻塞
		// SynDemo synDemo1 = new SynDemo();
		// SynDemo synDemo2 = new SynDemo();
		// new Thread(synDemo1,"mt1").start();
		// new Thread(synDemo2,"mt2").start();

		//场景5： 锁的是类，所有类的对象都会收阻塞
		SynDemo synDemo1 = new SynDemo();
		SynDemo synDemo2 = new SynDemo();
		new Thread(synDemo1,"thread-1").start();
		new Thread(synDemo1,"thread-2").start();
	}


}
