package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.simpleapi.SetConfig;
import com.srnpr.zapweb.simpleapi.SimpleSet;
import com.srnpr.zapweb.simpleapi.SimpleSetInput;
import com.srnpr.zapweb.simpleapi.SimpleSetResult;
import com.srnpr.zapweb.webapi.RootApiForToken;

public class MyConfig extends SimpleSet {

	public SimpleSetResult Process(SimpleSetInput inputParam,
			MDataMap mRequestMap) {

		return upResult(inputParam);
	}

	public SetConfig upConfig() {
		SetConfig setConfig = new SetConfig();

		setConfig.setPageCode("page_edit_m_yh_app_uset");

		return setConfig;
	}

}
