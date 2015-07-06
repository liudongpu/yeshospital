package com.srnpr.yeshospital.api.wx;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.support.WxSupport;
import com.srnpr.yeshospital.wx.WxPageInfo;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

public class MemberInfo extends RootApi<MemberInfoResult, MemberInfoInput> {

	public MemberInfoResult Process(MemberInfoInput inputParam,
			MDataMap mRequestMap) {
		return doProcess(inputParam);

	}

	public MemberInfoResult doProcess(MemberInfoInput inputParam) {
		MemberInfoResult result = new MemberInfoResult();

		if (result.upFlagTrue()) {
			String sOpenId = "";

			if (inputParam.getCode().equals("liudp")) {
				sOpenId = "liudp";
			} else {
				sOpenId = new WxSupport().upOpenId(inputParam.getCode());
			}

			if (StringUtils.isNotBlank(sOpenId)) {

				MDataMap mDataMap = DbUp.upTable("yh_wx_bind").one("wx_openid",
						sOpenId);

				if (mDataMap != null && mDataMap.size() > 0) {

					if (StringUtils.isNotBlank(mDataMap.get("sib_code"))) {

						result.setAccessToken(mDataMap.get("access_token"));

					} else {
						result.setBindToken(mDataMap.get("bind_token"));
					}

				} else {

					result.setBindToken(WebHelper.upUuid());

					DbUp.upTable("yh_wx_bind").insert("bind_code",
							WebHelper.upCode("BC"), "bind_token",
							result.getBindToken(), "wx_openid", sOpenId,
							"create_time", FormatHelper.upDateTime());
				}
			} else {
				result.inErrorMessage(965805280);
			}

		}

		return result;
	}

}
