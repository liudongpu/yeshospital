package com.srnpr.yeshospital.api.user;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class UserLoginApiResult extends RootResultWeb {

	@ZapcomApi(value = "用户授权码")
	private String userToken = "";

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}
