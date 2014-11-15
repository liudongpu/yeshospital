package com.srnpr.yeshospital.api.notice;

import com.srnpr.yeshospital.api.result.WarnListResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForToken;

public class ReadedWarn extends RootApiForToken<WarnListResult, RootInput> {

	public WarnListResult Process(RootInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new WarnListResult();
	}

}
