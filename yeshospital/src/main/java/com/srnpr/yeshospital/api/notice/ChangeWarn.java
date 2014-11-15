package com.srnpr.yeshospital.api.notice;

import com.srnpr.yeshospital.api.input.CommonChangeInfoInput;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class ChangeWarn extends RootApiForToken<RootResultWeb, CommonChangeInfoInput> {

	public RootResultWeb Process(CommonChangeInfoInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new RootResultWeb();
	}

}
