package com.srnpr.yeshospital.api.postdata;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.api.model.ElectrocardiogramStructInput;
import com.srnpr.yeshospital.model.PostDataApi;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webmethod.WebUpload;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ApiElectrocardiogramStruct extends
		PostDataApi<PostDataResult, ElectrocardiogramStructInput> {

	public PostDataResult toPost(ElectrocardiogramStructInput tInput,
			String sLogCode, String sManageCode) {
		PostDataResult postDataResult = new PostDataResult();

		// 检测并初始化
		if (postDataResult.upFlagTrue()) {
			postDataResult.inOtherResult(checkAndInit(tInput));
		}

		if (postDataResult.upFlagTrue()) {

			DbUp.upTable("yh_post_electrocardiogram").insert("post_code",
					WebHelper.upCode("PG"), "member_code", upMemberCode(),
					"log_code", sLogCode, "create_time",
					FormatHelper.upDateTime(), "test_result",
					tInput.getStructMessage().toString(),"post_time",tInput.getPostProcessTime());

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(updateReport("electrocardiogram_info",
					bInfo(965805113, tInput.getStructMessage()),
					"electrocardiogram_update"));

		}

		//

		return postDataResult;
	}

	public PostDataResult Process(ElectrocardiogramStructInput inputParam,
			MDataMap mRequestMap) {
		return upResult(inputParam, "", getManageCode());
	}

}
