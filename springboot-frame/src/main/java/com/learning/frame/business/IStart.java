package com.learning.frame.business;

import java.util.ArrayList;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 11:46]
 */
public interface IStart {
	ArrayList<String> startLoad();

	void initLog();

	void initPrivateParams();

	void initServices() throws Exception;

	void initWfDefine();

	void initHotSwap() throws Exception;
}
