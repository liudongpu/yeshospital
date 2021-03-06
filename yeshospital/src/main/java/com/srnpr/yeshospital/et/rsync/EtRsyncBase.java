package com.srnpr.yeshospital.et.rsync;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.et.model.EtConfigRsync;
import com.srnpr.yeshospital.helper.LogHttpHelper;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basehelper.EncodeHelper;
import com.srnpr.zapcom.basehelper.MapHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapweb.webdo.WebConst;

public abstract class EtRsyncBase extends BaseClass {

	public abstract EtConfigRsync getConfigRsync();

	/**
	 * token
	 * 
	 */
	private static String token = "";

	/**
	 * 最近一次同步时间
	 */
	private static String lastTime = "";

	/**
	 * 获取http请求
	 * 
	 * @param mDataMap
	 * @return
	 */
	public String upHttp(MDataMap mDataMap) {

		String sReturn = "";

		EtConfigRsync etConfigRsync = getConfigRsync();

		// 如果是需要token信息
		if (etConfigRsync.getFlagToken() == 1) {

			// 如果token不存在或者已过期 则重新生成token
			if (token.equals("") || lastTime.equals("")
					|| lastTime.compareTo(DateHelper.upDateTimeAdd("-9h")) < 0) {
				initToken();
			}

			mDataMap.inAllValues("token", token);

		}

		List<String> strings = new ArrayList<String>();

		for (String sKey : mDataMap.upKeys()) {

			strings.add(sKey + "=" + EncodeHelper.urlEncode(mDataMap.get(sKey)));

		}

		String sTarget = bConfig("yeshospital.et_address")
				+ etConfigRsync.getUrl();

		String sRequestData = StringUtils.join(strings, "&");

		String sBaseUrl = sTarget + "?" + sRequestData;

		String sLogCode = LogHttpHelper.AddLog("46580001000200040001", sTarget,
				EncodeHelper.urlDecode(sRequestData));

		try {
			sReturn = WebClientSupport.create().doGet(sBaseUrl);
			LogHttpHelper.UpdateLog(sLogCode, sReturn, 1, 1, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return sReturn;

	}

	/**
	 * 初始化token信息
	 */
	private void initToken() {

		EtRsyncAuth etRsyncAuth = new EtRsyncAuth();
		String sResponseString = etRsyncAuth.upHttp(new MDataMap("entAccount",
				bConfig("yeshospital.et_account"), "password",
				bConfig("yeshospital.et_password")));
		token = sResponseString;
		lastTime = DateHelper.upNow();
	}

}
