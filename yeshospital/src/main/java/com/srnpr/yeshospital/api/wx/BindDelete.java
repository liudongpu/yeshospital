package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;

public class BindDelete extends RootApi<BindCheckResult, BindDeleteInput> {

	public BindCheckResult Process(BindDeleteInput inputParam, MDataMap mRequestMap) {
		BindCheckResult result = new BindCheckResult();
		if (result.upFlagTrue()) {

			DbUp.upTable("yh_sib_info").delete("sib_code", inputParam.getSibCode(), "member_code",
					inputParam.getMemberCode());

		}
		return result;
	}

}
