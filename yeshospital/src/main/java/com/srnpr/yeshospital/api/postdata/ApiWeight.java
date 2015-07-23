package com.srnpr.yeshospital.api.postdata;

import com.srnpr.yeshospital.api.model.OxygenInput;
import com.srnpr.yeshospital.api.model.WeightInput;
import com.srnpr.yeshospital.model.PostDataApi;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.yeshospital.support.WarnSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ApiWeight extends PostDataApi<PostDataResult, WeightInput> {

	public PostDataResult toPost(WeightInput tInput, String sLogCode,
			String sManageCode) {
		PostDataResult postDataResult = new PostDataResult();

		// 检测并初始化
		if (postDataResult.upFlagTrue()) {
			postDataResult.inOtherResult(checkAndInit(tInput));
		}

		if (postDataResult.upFlagTrue()) {
			DbUp.upTable("yh_post_weight").insert("post_code",
					WebHelper.upCode("PG"), "member_code", upMemberCode(),
					"log_code", sLogCode, "create_time",
					FormatHelper.upDateTime(), "weight",
					tInput.getWeight().toString(), "post_time",
					tInput.getPostProcessTime());

			/*
			 * WarnSupport warnSupport = new WarnSupport();
			 * postDataResult.inOtherResult
			 * (warnSupport.warnCheck(upMemberCode(), new
			 * WarnCheckInfo("46580001000300010005", "46580001000300030006",
			 * tInput.getWeight())));
			 */

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(toProcess(tInput));

		}

		//

		return postDataResult;
	}

	public PostDataResult Process(WeightInput inputParam, MDataMap mRequestMap) {
		return upResult(inputParam, "", getManageCode());
	}

	public MWebResult toProcess(WeightInput tInput) {
		MWebResult mWebResult = new MWebResult();

		if (mWebResult.upFlagTrue()) {
			mWebResult.inOtherResult(updateReport("weight_info",
					bInfo(965805111, tInput.getWeight()), "weight_update"));
		}

		return mWebResult;
	}

}
