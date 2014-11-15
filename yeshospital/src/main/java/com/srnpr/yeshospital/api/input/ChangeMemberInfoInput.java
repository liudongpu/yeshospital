package com.srnpr.yeshospital.api.input;

import com.srnpr.yeshospital.api.model.MemberInfo;
import com.srnpr.yeshospital.face.IChangeType;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class ChangeMemberInfoInput extends CommonChangeTypeInput implements
		IChangeType {

	@ZapcomApi(value = "老人信息")
	private MemberInfo memberInfo = new MemberInfo();

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

}
