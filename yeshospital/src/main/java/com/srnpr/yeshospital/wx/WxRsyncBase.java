package com.srnpr.yeshospital.wx;

import java.nio.charset.Charset;

import com.srnpr.yeshospital.wx.model.WxAccessToken;
import com.srnpr.yeshospital.wx.model.WxConfigRsync;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.GsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapcom.topdo.TopConst;

public abstract class WxRsyncBase extends BaseClass {

	public abstract WxConfigRsync getWxConfigRsync();

	/**
	 * token
	 * 
	 */
	private static String token = "";

	/**
	 * 过期时间
	 */
	private static String lastTime = "";

	public String upHttp(String sPost) {

		String sReturn = "";

		if (token.equals("") || lastTime.equals("")
				|| lastTime.compareTo(DateHelper.upNow()) < 0) {
			initToken();
		}

		String sTarget = bConfig("yeshospital.wx_url_base")
				+ getWxConfigRsync().getUrl() + token;

		try {
			sReturn = WebClientSupport.create().upRequest(sTarget, new String(sPost.getBytes(TopConst.CONST_BASE_ENCODING), "ISO-8859-1"));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return sReturn;

	}

	private void initToken() {

		try {

			String sUrl = FormatHelper.formatString(
					bConfig("yeshospital.wx_url_access"),
					bConfig("yeshospital.wx_appid"),
					bConfig("yeshospital.wx_secret"));

			String sResponse = WebClientSupport.create().doGet(sUrl);

			WxAccessToken wxAccessToken = new WxAccessToken();

			wxAccessToken = new GsonHelper().fromJson(sResponse, wxAccessToken);

			token = wxAccessToken.getAccess_token();
			// 过期时间 当前时间加微信过期时间减一小时
			lastTime = DateHelper.upDateTimeAdd(String.valueOf(wxAccessToken
					.getExpires_in() - 3600) + "s");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
