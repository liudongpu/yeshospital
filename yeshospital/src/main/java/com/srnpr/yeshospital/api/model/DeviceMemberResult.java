package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeviceMemberResult extends RootResultWeb {

	@ZapcomApi(value = "用户基本信息", require = 0, remark = "用户信息实体")
	private MemberInfo member = new MemberInfo();

	public MemberInfo getMember() {
		return member;
	}

	public void setMember(MemberInfo member) {
		this.member = member;
	}

}
