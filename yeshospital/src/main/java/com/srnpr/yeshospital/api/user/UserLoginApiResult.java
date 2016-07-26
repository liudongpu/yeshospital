package com.srnpr.yeshospital.api.user;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.usermodel.MUserInfo;
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
	
	
	private MUserInfo userInfo=new MUserInfo();

	public MUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(MUserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
