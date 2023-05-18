package com.learning.juc.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/5/18 17:05]
 */
public class GCTargetWeakReference extends WeakReference<GCTarget> {
	public String id;

	public GCTargetWeakReference(GCTarget gcTarget,
								 ReferenceQueue<? super GCTarget> queue) {
		super(gcTarget, queue);
		this.id = gcTarget.id;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalizing GCTargetWeakReference " + id);
	}
}
