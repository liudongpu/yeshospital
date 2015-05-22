package com.srnpr.yeshospital.api.postdata;

import com.srnpr.yeshospital.api.model.WeightInput;
import com.srnpr.yeshospital.model.PostDataApi;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.yeshospital.support.WarnSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

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
					tInput.getWeight().toString());

			WarnSupport warnSupport = new WarnSupport();

			/*
			 * postDataResult.inOtherResult(warnSupport.warnCheck(upMemberCode(),
			 * new WarnCheckInfo("46580001000300010005", "46580001000300030006",
			 * tInput.getWeight())));
			 */

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(updateReport("weight_info",
					bInfo(965805108, tInput.getWeight()), "weight_update"));

		}

		//

		return postDataResult;
	}

	public PostDataResult Process(WeightInput inputParam, MDataMap mRequestMap) {
		return upResult(inputParam, "", getManageCode());
	}

}
