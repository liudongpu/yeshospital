package com.srnpr.yeshospital.func;

import java.math.BigDecimal;

import com.srnpr.yeshospital.api.model.GlucoseInput;
import com.srnpr.yeshospital.api.model.OxygenInput;
import com.srnpr.yeshospital.api.model.TemperatureInput;
import com.srnpr.yeshospital.api.postdata.ApiGlucose;
import com.srnpr.yeshospital.api.postdata.ApiOxygen;
import com.srnpr.yeshospital.api.postdata.ApiTemperature;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncPostTemperature extends FuncPostBase {

	public MWebResult toCheck(MDataMap mDataMap) {
		ApiTemperature api = new ApiTemperature();

		api.fixMemberCode(mDataMap.get("member_code"));
		
		

		TemperatureInput input = new TemperatureInput();

		input.setDataTemperature(new BigDecimal(mDataMap.get("temperature")));
		
		input.setPostServerCode(mDataMap.get("post_code"));
		
		input.setPostProcessTime(mDataMap.get("post_time"));
		
		return api.toProcess(input);
	}

}
