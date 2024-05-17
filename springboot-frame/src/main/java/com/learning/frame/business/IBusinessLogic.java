package com.learning.frame.business;

import net.sf.json.JSONObject;

public interface IBusinessLogic {
	ReturnResult runLogicLocal(JSONObject params, JSONObject userObject, JSONObject service);
}
