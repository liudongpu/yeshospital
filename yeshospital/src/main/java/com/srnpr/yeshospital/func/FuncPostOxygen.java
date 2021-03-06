package com.srnpr.yeshospital.func;

import java.math.BigDecimal;

import com.srnpr.yeshospital.api.model.OxygenInput;
import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.api.postdata.ApiOxygen;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncPostOxygen extends FuncPostBase {

	public MWebResult toCheck(MDataMap mDataMap) {
		ApiOxygen api = new ApiOxygen();

		api.fixMemberCode(mDataMap.get("member_code"));

		OxygenInput input = new OxygenInput();
		input.setDataHeart(new BigDecimal(mDataMap.get("heart_rate")));
		input.setDataOxygen(new BigDecimal(mDataMap.get("oxygen")));
		input.setPostServerCode(mDataMap.get("post_code"));
		input.setPostProcessTime(mDataMap.get("post_time"));
		return api.toProcess(input);
	}

}
