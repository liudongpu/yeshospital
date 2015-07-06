package com.srnpr.yeshospital.api.wx;

import com.srnpr.yeshospital.helper.MessageHelper;
import com.srnpr.yeshospital.support.VerifySupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;

public class VerifyApi extends RootApi<VerifyApiResult, VerifyApiInput> {

	public VerifyApiResult Process(VerifyApiInput inputParam,
			MDataMap mRequestMap) {
		VerifyApiResult verifyApiResult = new VerifyApiResult();

		new VerifySupport().VerifySend("", inputParam.getMobilePhone(), 4, "600s");

		return verifyApiResult;
	}

}
