package com.srnpr.yeshospital.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basesupport.WebClientSupport;

public class WxSupport extends BaseClass {

	public String upOpenId(String sCode) {

		String sReturn = "";

		String sUrl = FormatHelper.formatString(
				bConfig("yeshospital.wx_url_openid"),
				bConfig("yeshospital.wx_appid"),
				bConfig("yeshospital.wx_secret"), sCode);

		try {
			String sResponse = WebClientSupport.create().doGet(sUrl);

			JSONObject jsonObject = JSON.parseObject(sResponse);

			sReturn = jsonObject.getString("openid");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return sReturn;
	}

}
