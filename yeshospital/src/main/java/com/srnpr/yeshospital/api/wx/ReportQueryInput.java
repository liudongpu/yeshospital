package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class ReportQueryInput extends RootInput {

	@ZapcomApi(value = "日期字符串")
	private String dateString = "";

	@ZapcomApi(value = "用户编号")
	private String memberCode = "";

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

}
