package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeviceAuthResult extends RootResultWeb {

	@ZapcomApi(value = "识别认证码", remark = "调用其他接口时必须传入，唯一识别设备信息", demo = "1234abcdefg")
	private String oauth_token = "";

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

}
