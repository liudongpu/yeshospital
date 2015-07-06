package com.srnpr.yeshospital.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.srnpr.yeshospital.api.wx.MemberInfo;
import com.srnpr.yeshospital.api.wx.MemberInfoInput;
import com.srnpr.yeshospital.api.wx.MemberInfoResult;
import com.srnpr.yeshospital.support.WxSupport;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class WxPageInfo extends BaseClass {

	public String upOpenId() {

		String sCode = WebSessionHelper.create().upRequest("code");

		return new WxSupport().upOpenId(sCode);
	}

	public MemberInfoResult upMemberInfo() {
		String sCode = WebSessionHelper.create().upRequest("code");

		MemberInfoInput input = new MemberInfoInput();
		input.setCode(sCode);

		return new MemberInfo().doProcess(input);

	}

}
