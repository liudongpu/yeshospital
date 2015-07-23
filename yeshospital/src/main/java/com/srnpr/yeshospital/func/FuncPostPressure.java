package com.srnpr.yeshospital.func;

import java.math.BigDecimal;

import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.api.postdata.ApiPressure;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncPostPressure extends FuncPostBase {

	public MWebResult toCheck(MDataMap mDataMap) {
		ApiPressure api = new ApiPressure();

		api.fixMemberCode(mDataMap.get("member_code"));

		PressureInput input = new PressureInput();
		input.setDataHeart(new BigDecimal(mDataMap.get("heart_rate")));
		input.setDataLower(new BigDecimal(mDataMap.get("lower_pressure")));
		input.setDataUpper(new BigDecimal(mDataMap.get("upper_pressure")));

		return api.toProcess(input);
	}

}
