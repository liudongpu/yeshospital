package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapweb.webapi.RootResultWeb;

public class BindSubmitResult extends RootResultWeb {

	private String linkCode = "";

	public String getLinkCode() {
		return linkCode;
	}

	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}

}
