package com.srnpr.yeshospital.api.postdata;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.api.model.ElectrocardiogramImageInput;
import com.srnpr.yeshospital.api.model.WeightInput;
import com.srnpr.yeshospital.model.PostDataApi;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.yeshospital.support.WarnSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webmethod.WebUpload;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ApiElectrocardiogramImage extends
		PostDataApi<PostDataResult, ElectrocardiogramImageInput> {

	public PostDataResult toPost(ElectrocardiogramImageInput tInput,
			String sLogCode, String sManageCode) {
		PostDataResult postDataResult = new PostDataResult();

		// 检测并初始化
		if (postDataResult.upFlagTrue()) {
			postDataResult.inOtherResult(checkAndInit(tInput));
		}

		String sUrl = "";

		if (postDataResult.upFlagTrue()) {

			/*
			 * DbUp.upTable("yh_post_weight").insert("post_code",
			 * WebHelper.upCode("PG"), "member_code", upMemberCode(),
			 * "log_code", sLogCode, "create_time", FormatHelper.upDateTime(),
			 * "weight", tInput.getWeight().toString());
			 * 
			 * WarnSupport warnSupport = new WarnSupport();
			 * 
			 * 
			 * postDataResult.inOtherResult(warnSupport.warnCheck(upMemberCode(),
			 * new WarnCheckInfo("46580001000300010005", "46580001000300030006",
			 * tInput.getWeight())));
			 */

			if (StringUtils.isBlank(tInput.getImageType())) {

				byte[] bImages = Base64.decodeBase64(tInput.getImageData());

				MWebResult mWebResult = WebUpload.create().remoteUpload(
						"ecgimage", WebHelper.upUuid() + ".png", bImages);

				postDataResult.inOtherResult(mWebResult);

				if (mWebResult.upFlagTrue()) {
					sUrl = mWebResult.getResultObject().toString();
				}

			}

		}

		// 插入报告日志信息
		if (postDataResult.upFlagTrue()) {

			postDataResult.inOtherResult(updateReport("image_info", sUrl,
					"image_update"));

		}

		//

		return postDataResult;
	}

	public PostDataResult Process(ElectrocardiogramImageInput inputParam,
			MDataMap mRequestMap) {
		return upResult(inputParam, "", getManageCode());
	}

}
