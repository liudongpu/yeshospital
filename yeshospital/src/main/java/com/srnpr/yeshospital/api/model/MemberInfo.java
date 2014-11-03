package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class MemberInfo {

	@ZapcomApi(value = "用户编号", require = 0, remark = "用户编号", demo = "MI001")
	private String memberCode="";
	
	
	@ZapcomApi(value = "用户姓名", require = 0, remark = "用户姓名", demo = "黎明")
	private String memberName="";
	
	@ZapcomApi(value = "用户手机号码", require = 0, remark = "用户手机号码", demo = "13512345678")
	private String memberPhone="";

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
}
