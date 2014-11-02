package com.srnpr.yeshospital.api.postdata;

import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.api.model.TemperatureInput;
import com.srnpr.yeshospital.model.PostDataApi;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.yeshospital.support.WarnSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

public class ApiTemperature extends
		PostDataApi<PostDataResult, TemperatureInput> {

	public PostDataResult Process(TemperatureInput inputParam, MDataMap mRequestMap) {

		return upResult(inputParam, "", getManageCode());
	}
	
	
	public PostDataResult toPost(TemperatureInput tInput, String sLogCode,
			String sManageCode) {
		PostDataResult postDataResult = new PostDataResult();

		// 检测并初始化
		if (postDataResult.upFlagTrue()) {
			postDataResult.inOtherResult(checkAndInit(tInput));
		}

		if (postDataResult.upFlagTrue()) {
			DbUp.upTable("yh_post_temperature").insert("post_code",
					WebHelper.upCode("PP"), "member_code", upMemberCode(),
					"log_code", sLogCode, "create_time",
					FormatHelper.upDateTime(), "temperature",
					tInput.getDataTemperature().toString());

			WarnSupport warnSupport = new WarnSupport();

			postDataResult.inOtherResult(warnSupport.warnCheck(upMemberCode(),
					new WarnCheckInfo("46580001000300010001",
							"46580001000300030004", tInput.getDataTemperature())));

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(updateReport("temperature_info",
					bInfo(965805107, tInput.getDataTemperature()),
					"temperature_update"));

		}

		//

		return postDataResult;
	}

}
