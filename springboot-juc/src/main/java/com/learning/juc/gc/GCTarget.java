package com.learning.juc.gc;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/5/18 17:05]
 */
public class GCTarget {
	// 对象的ID
	public String id;

	// 占用内存空间
	byte[] buffer = new byte[1024];

	public GCTarget(String id) {
		this.id = id;
	}

	protected void finalize() throws Throwable {
		// 执行垃圾回收时打印显示对象ID
		System.out.println("Finalizing GCTarget, id is : " + id);
	}
}
