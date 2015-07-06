package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class MemberInfoResult extends RootResultWeb {

	@ZapcomApi(value = "用户访问令牌，如果为空时表明用户尚未绑定")
	private String accessToken = "";

	@ZapcomApi(value = "绑定的令牌，该令牌和用户访问令牌不能同时为空")
	private String bindToken = "";
	
	@ZapcomApi(value = "用户编号")
	private String sibCode="";
	

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getBindToken() {
		return bindToken;
	}

	public void setBindToken(String bindToken) {
		this.bindToken = bindToken;
	}

	public String getSibCode() {
		return sibCode;
	}

	public void setSibCode(String sibCode) {
		this.sibCode = sibCode;
	}

}
