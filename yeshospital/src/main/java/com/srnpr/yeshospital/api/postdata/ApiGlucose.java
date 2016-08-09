package com.srnpr.yeshospital.api.postdata;

import com.srnpr.yeshospital.api.model.GlucoseInput;
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

public class ApiGlucose extends PostDataApi<PostDataResult, GlucoseInput> {
	public PostDataResult Process(GlucoseInput inputParam, MDataMap mRequestMap) {

		return upResult(inputParam, "", getManageCode());
	}

	public PostDataResult toPost(GlucoseInput tInput, String sLogCode,
			String sManageCode) {
		PostDataResult postDataResult = new PostDataResult();

		// 检测并初始化
		if (postDataResult.upFlagTrue()) {
			postDataResult.inOtherResult(checkAndInit(tInput));
		}

		
		
		if (postDataResult.upFlagTrue()) {
			
			tInput.setPostServerCode(WebHelper.upCode("PC"));
			
			DbUp.upTable("yh_post_glucose").insert("post_code",
					tInput.getPostServerCode(), "member_code", upMemberCode(),
					"log_code", sLogCode, "create_time",
					FormatHelper.upDateTime(), "glucose",
					tInput.getDataGlucose().toString(), "test_type",
					tInput.getDataCheckType(), "post_time",
					tInput.getPostProcessTime());

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(toProcess(tInput));

		}

		//

		return postDataResult;
	}

	public MWebResult toProcess(GlucoseInput tInput) {
		MWebResult mWebResult = new MWebResult();

		/*
		if (mWebResult.upFlagTrue()) {
			WarnSupport warnSupport = new WarnSupport();

			// 如果是餐后测试
			if (tInput.getDataCheckType().equals("46580001000300070001")) {

				mWebResult.inOtherResult(warnSupport.warnCheck(upMemberCode(),
						new WarnCheckInfo("46580001000300010005",
								"46580001000300030005",
								tInput.getDataGlucose(), tInput.getPostServerCode())));

			} else {

				mWebResult.inOtherResult(warnSupport.warnCheck(upMemberCode(),
						new WarnCheckInfo("46580001000300010005",
								"46580001000300030006",
								tInput.getDataGlucose(), tInput.getPostServerCode())));
			}
		}
		*/

		if (mWebResult.upFlagTrue()) {
			mWebResult
					.inOtherResult(updateReport("glucose_info",
							bInfo(965805108, tInput.getDataGlucose()),
							"glucose_update"));
		}

		return mWebResult;
	}

}
