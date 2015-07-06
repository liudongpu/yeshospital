package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class BindSubmitInput extends RootInput {

	@ZapcomApi(value = "与老人关系", require = 1)
	private String relationCode = "";
	@ZapcomApi(value = "本人姓名", require = 1)
	private String myName = "";
	@ZapcomApi(value = "手机号码", require = 1)
	private String mobilePhone = "";
	@ZapcomApi(value = "验证码", require = 1)
	private String verifyCode = "";
	@ZapcomApi(value = "老人唯一编号")
	private String memberUid = "";
	@ZapcomApi(value = "绑定关系编号")
	private String bindToken = "";

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getMemberUid() {
		return memberUid;
	}

	public void setMemberUid(String memberUid) {
		this.memberUid = memberUid;
	}

	public String getBindToken() {
		return bindToken;
	}

	public void setBindToken(String bindToken) {
		this.bindToken = bindToken;
	}

}
