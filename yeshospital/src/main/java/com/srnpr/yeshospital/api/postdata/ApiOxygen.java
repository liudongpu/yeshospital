package com.srnpr.yeshospital.api.postdata;

import com.srnpr.yeshospital.api.model.GlucoseInput;
import com.srnpr.yeshospital.api.model.OxygenInput;
import com.srnpr.yeshospital.api.model.TemperatureInput;
import com.srnpr.yeshospital.model.PostDataApi;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.yeshospital.support.WarnSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ApiOxygen extends PostDataApi<PostDataResult, OxygenInput> {
	public PostDataResult Process(OxygenInput inputParam, MDataMap mRequestMap) {

		return upResult(inputParam, "", getManageCode());
	}

	public PostDataResult toPost(OxygenInput tInput, String sLogCode,
			String sManageCode) {
		PostDataResult postDataResult = new PostDataResult();

		// 检测并初始化
		if (postDataResult.upFlagTrue()) {
			postDataResult.inOtherResult(checkAndInit(tInput));
		}

		if (postDataResult.upFlagTrue()) {
			tInput.setPostServerCode(WebHelper.upCode("PC"));
			DbUp.upTable("yh_post_oxygen").insert("post_code",
					tInput.getPostServerCode(), "member_code", upMemberCode(),
					"log_code", sLogCode, "create_time",
					FormatHelper.upDateTime(), "oxygen",
					tInput.getDataOxygen().toString(), "heart_rate",
					tInput.getDataHeart().toString(), "post_time",
					tInput.getPostProcessTime());

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(toProcess(tInput));

		}

		//

		return postDataResult;
	}

	public MWebResult toProcess(OxygenInput tInput) {
		MWebResult mWebResult = new MWebResult();

		/*
		if (mWebResult.upFlagTrue()) {
			WarnSupport warnSupport = new WarnSupport();

			mWebResult
					.inOtherResult(warnSupport.warnCheck(
							upMemberCode(),
							new WarnCheckInfo("46580001000300010006",
									"46580001000300030003", tInput
											.getDataOxygen(), tInput
											.getPostServerCode()),
							new WarnCheckInfo("46580001000300010007",
									"46580001000300030007", tInput
											.getDataHeart(), tInput
											.getPostServerCode())));
		}
		*/

		if (mWebResult.upFlagTrue()) {
			mWebResult.inOtherResult(updateReport("oxygen_info",
					bInfo(965805109, tInput.getDataOxygen()), "oxygen_update"));
		}

		return mWebResult;
	}

}
