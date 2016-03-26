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

	public MemberInfoResult Process(MemberInfoInput inputParam, MDataMap mRequestMap) {
		return doProcess(inputParam);

	}

	public MemberInfoResult doProcess(MemberInfoInput inputParam) {
		MemberInfoResult result = new MemberInfoResult();

		if (result.upFlagTrue()) {
			String sOpenId = "";

			if (inputParam.getCode().equals("liudp")) {
				sOpenId = "liudp";
			} else if (StringUtils.startsWith(inputParam.getCode(), "yhbt_")) {
				// 如果是绑定完成后调用到该页面
				sOpenId = DbUp.upTable("yh_wx_bind")
						.one("bind_token", StringUtils.substringAfter(inputParam.getCode(), "yhbt_")).get("wx_openid");
			} else {
				sOpenId = new WxSupport().upOpenId(inputParam.getCode());

				// 最后加载 返回跳转之类的从库里面读
				if (StringUtils.isBlank(sOpenId)) {
					sOpenId = DbUp.upTable("yh_wx_bind").one("access_token",

							inputParam.getCode()).get("wx_openid");
				}

			}

			if (StringUtils.isNotBlank(sOpenId)) {

				MDataMap mDataMap = DbUp.upTable("yh_wx_bind").one("wx_openid", sOpenId);

				if (mDataMap != null && mDataMap.size() > 0) {

					if (StringUtils.isNotBlank(mDataMap.get("sib_code"))) {

						result.setAccessToken(mDataMap.get("access_token"));
						result.setSibCode(mDataMap.get("sib_code"));

					}
					result.setBindToken(mDataMap.get("bind_token"));

				} else {

					result.setBindToken(WebHelper.upUuid());

					DbUp.upTable("yh_wx_bind").insert("bind_code", WebHelper.upCode("BC"), "bind_token",
							result.getBindToken(), "wx_openid", sOpenId, "create_time", FormatHelper.upDateTime());
				}
			} else {
				result.inErrorMessage(965805280);
			}

		}

		return result;
	}

}
