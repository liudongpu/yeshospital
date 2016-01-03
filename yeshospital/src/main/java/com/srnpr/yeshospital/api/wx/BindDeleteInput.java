package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class BindDeleteInput extends RootInput {

	@ZapcomApi(value = "亲属编号", require = 1)
	private String sibCode = "";
	@ZapcomApi(value = "用户编号", require = 1)
	private String memberCode = "";

	public String getSibCode() {
		return sibCode;
	}

	public void setSibCode(String sibCode) {
		this.sibCode = sibCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

}
