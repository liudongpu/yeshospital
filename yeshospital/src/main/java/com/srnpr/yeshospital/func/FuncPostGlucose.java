package com.srnpr.yeshospital.func;

import java.math.BigDecimal;

import com.srnpr.yeshospital.api.model.GlucoseInput;
import com.srnpr.yeshospital.api.model.OxygenInput;
import com.srnpr.yeshospital.api.postdata.ApiGlucose;
import com.srnpr.yeshospital.api.postdata.ApiOxygen;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncPostGlucose extends FuncPostBase {

	public MWebResult toCheck(MDataMap mDataMap) {
		ApiGlucose api = new ApiGlucose();

		api.fixMemberCode(mDataMap.get("member_code"));

		GlucoseInput input = new GlucoseInput();

		input.setDataGlucose(new BigDecimal(mDataMap.get("glucose")));
		input.setPostServerCode(mDataMap.get("post_code"));
		input.setPostProcessTime(mDataMap.get("post_time"));
		return api.toProcess(input);
	}

}
