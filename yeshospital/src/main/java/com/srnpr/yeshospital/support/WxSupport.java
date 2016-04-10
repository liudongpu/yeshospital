package com.srnpr.yeshospital.support;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class WxSupport extends BaseClass {

	public String upOpenId(String sCode) {

		String sReturn = "";
		String sUrl = FormatHelper.formatString(bConfig("yeshospital.wx_url_openid"), bConfig("yeshospital.wx_appid"),
				bConfig("yeshospital.wx_secret"), sCode);

		String sRequestUrl = WebSessionHelper.create().upHttpRequest().getRequestURL().toString();
		if (StringUtils.isNotBlank(sRequestUrl)) {

			if (StringUtils.contains(sRequestUrl, ".wei.")) {

				sRequestUrl = StringUtils.substringBefore(sRequestUrl, ".");

				if (StringUtils.contains(sRequestUrl, "/")) {
					sRequestUrl = StringUtils.substringAfterLast(sRequestUrl, "/");
				}
				if (StringUtils.contains(sRequestUrl, "%2f")) {
					sRequestUrl = StringUtils.substringAfterLast(sRequestUrl, "%2f");
				}

				if (StringUtils.isNotBlank(sRequestUrl)) {
					sUrl = FormatHelper.formatString(bConfig("yeshospital.wx_url_openid"),
							bConfig("yeshospital.wei_appid_" + sRequestUrl),
							bConfig("yeshospital.wei_secret_" + sRequestUrl), sCode);
				}

			}

		}

		bLogInfo(965805284, sUrl);
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
