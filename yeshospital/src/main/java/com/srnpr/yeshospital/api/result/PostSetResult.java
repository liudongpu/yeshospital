package com.srnpr.yeshospital.api.result;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class PostSetResult extends RootResultWeb {

	@ZapcomApi(value="上传定时",demo="10")
	private String postTime="";

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
	
}
