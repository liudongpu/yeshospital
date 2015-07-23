package com.srnpr.yeshospital.api.postdata;

import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.model.PostDataApi;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.yeshospital.support.WarnSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webapi.RootApiForManage;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ApiPressure extends PostDataApi<PostDataResult, PressureInput> {
	public PostDataResult Process(PressureInput inputParam, MDataMap mRequestMap) {

		return upResult(inputParam, "", getManageCode());
	}

	public PostDataResult toPost(PressureInput tInput, String sLogCode,
			String sManageCode) {

		PostDataResult postDataResult = new PostDataResult();

		// 检测并初始化
		if (postDataResult.upFlagTrue()) {
			postDataResult.inOtherResult(checkAndInit(tInput));
		}

		if (postDataResult.upFlagTrue()) {
			DbUp.upTable("yh_post_pressure").insert("post_code",
					WebHelper.upCode("PP"), "member_code", upMemberCode(),
					"log_code", sLogCode, "create_time",
					FormatHelper.upDateTime(), "heart_rate",
					tInput.getDataHeart().toString(), "upper_pressure",
					tInput.getDataUpper().toString(), "lower_pressure",
					tInput.getDataLower().toString(), "post_time",
					tInput.getPostProcessTime());

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(toProcess(tInput));

		}

		return postDataResult;

	}

	public MWebResult toProcess(PressureInput tInput) {
		MWebResult mWebResult = new MWebResult();

		if (mWebResult.upFlagTrue()) {
			WarnSupport warnSupport = new WarnSupport();

			mWebResult.inOtherResult(warnSupport.warnCheck(upMemberCode(),
					new WarnCheckInfo("46580001000300010002",
							"46580001000300030001", tInput.getDataUpper()),
					new WarnCheckInfo("46580001000300010002",
							"46580001000300030002", tInput.getDataLower()),
					new WarnCheckInfo("46580001000300010007",
							"46580001000300030007", tInput.getDataHeart())));
		}

		if (mWebResult.upFlagTrue()) {
			mWebResult.inOtherResult(updateReport(
					"pressure_info",
					bInfo(965805106, tInput.getDataHeart(),
							tInput.getDataLower(), tInput.getDataUpper()),
					"pressure_update"));
		}

		return mWebResult;
	}

}
