package com.srnpr.yeshospital.api.member;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class ChangeUserPasswordInput extends RootInput {

	@ZapcomApi(value = "原密码", require = 1)
	private String oldPassword = "";

	@ZapcomApi(value = "新密码", require = 1)
	private String newPassword = "";

	@ZapcomApi(value = "重复新密码", require = 1)
	private String rePassword = "";

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}
