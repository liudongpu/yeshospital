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

public class ApiPressure extends PostDataApi<PostDataResult, PressureInput> {

	public PostDataResult toPost(PressureInput tInput, String sLogCode,
			String sManageCode) {

		PostDataResult postDataResult = new PostDataResult();

		postDataResult.inOtherResult(checkAndInit(tInput));

		if (postDataResult.upFlagTrue()) {
			DbUp.upTable("yh_post_pressure").insert("post_code",
					WebHelper.upCode("PP"), "member_code", upMemberCode(),
					"log_code", sLogCode, "create_time",
					FormatHelper.upDateTime(), "heart_rate",
					tInput.getDataHeart().toString(), "upper_pressure",
					tInput.getDataUpper().toString(), "lower_pressure",
					tInput.getDataLower().toString());

			WarnSupport warnSupport = new WarnSupport();

			warnSupport.warnCheck(upMemberCode(),
					new WarnCheckInfo("46580001000300010002",
							"46580001000300030001", tInput.getDataUpper()),
					new WarnCheckInfo("46580001000300010002",
							"46580001000300030002", tInput.getDataLower()));

		}

		//

		return postDataResult;

	}

}
