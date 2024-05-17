package com.learning.frame.basicframe;

import com.learning.frame.common.SystemCaches;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 11:55]
 */
public class ExtClassLoader extends URLClassLoader {

	public ExtClassLoader() {
		super(myUrls());
	}

	private static URL[] myUrls() {
		URL url = null;
		File x = new File(SystemCaches.get("INSTALL_HOME").toString() + "/hotswap");
		if (!x.exists()) {
			x.mkdirs();
		}
		try {
			url = x.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		return new URL[]{url};
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		return loadClass(name, false);
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class<?> clazz = findLoadedClass(name);
		if (clazz != null) {
			if (resolve) {
				resolveClass(clazz);
			}
			return clazz;
		}
		return customLoad(name, resolve);
	}

	private Class<?> customLoad(String name, boolean resolve) throws ClassNotFoundException {
		Class<?> clazz = null;
		if (name.startsWith("com.learning.business.impl")) {
			clazz = findClass(name);
		} else {
			ClassLoader system = Thread.currentThread().getContextClassLoader();
			clazz = system.loadClass(name);
		}
		if (resolve) {
			resolveClass(clazz);
		}
		return clazz;
	}

	public void loadPackage() throws Exception {
		List<File> files = new ArrayList<File>();
		File f = new File(SystemCaches.get("INSTALL_HOME").toString() + "/hotswap");
		loadClass(f.getName());
	}
}
