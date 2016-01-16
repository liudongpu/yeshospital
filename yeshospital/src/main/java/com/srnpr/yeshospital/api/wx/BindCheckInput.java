package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class BindCheckInput extends RootInput {

	@ZapcomApi(value = "身份证号码", require = 1)
	private String cardCode = "";

	@ZapcomApi(value = "老人姓名", require = 1)
	private String userName = "";

	@ZapcomApi(value = "绑定编号", require = 0)
	private String bindToken = "";

	public String getBindToken() {
		return bindToken;
	}

	public void setBindToken(String bindToken) {
		this.bindToken = bindToken;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
