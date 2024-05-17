package com.learning.frame.basicframe;

import com.learning.frame.business.IBusinessCache;
import com.learning.frame.business.IStart;
import com.learning.frame.common.SystemCaches;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 11:34]
 */
public class BFStart implements IStart {
	@Override
	public ArrayList<String> startLoad() {
		return null;
	}

	@Override
	public void initLog() {

	}

	@Override
	public void initPrivateParams() {

	}

	@Override
	public void initServices() throws Exception {
		String service_mgr = SystemCaches.get("service_mgr").toString();
		Class<?> IClass = FrameUtil.loadClass(service_mgr);
		IBusinessCache ibs = (IBusinessCache) IClass.newInstance();
		ibs.loadCaches(null);
		SystemCaches.blClient = new HashMap<>();
	}

	@Override
	public void initWfDefine() {

	}

	@Override
	public void initHotSwap() throws Exception {
		String system_hotswap = SystemCaches.get("system_hotswap") == null ? "N" : SystemCaches.get("system_hotswap").toString();
		if (system_hotswap.equals("Y")) {
			ExtClassLoader extClassLoader = new ExtClassLoader();
			extClassLoader.loadPackage();
			SystemCaches.classLoader = extClassLoader;
		} else {
			System.err.println("########hotswap Be Closed");
			SystemCaches.classLoader = null;
		}
	}
}
