package com.srnpr.yeshospital.api.notice;

import com.srnpr.yeshospital.api.input.DateBetweenInput;
import com.srnpr.yeshospital.api.result.WarnListResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webapi.RootApiForToken;

public class HistoryWarn extends
		RootApiForToken<WarnListResult, DateBetweenInput> {

	public WarnListResult Process(DateBetweenInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new WarnListResult();
	}

}
