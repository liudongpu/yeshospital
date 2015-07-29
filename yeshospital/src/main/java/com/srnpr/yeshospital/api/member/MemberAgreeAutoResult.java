package com.srnpr.yeshospital.api.member;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class MemberAgreeAutoResult extends RootResultWeb {
	@ZapcomApi(value = "养老院编号")
	private String geracomiumCode = "";
	@ZapcomApi(value = "建议信息")
	private String agreeInfo = "";

	public String getGeracomiumCode() {
		return geracomiumCode;
	}

	public void setGeracomiumCode(String geracomiumCode) {
		this.geracomiumCode = geracomiumCode;
	}

	public String getAgreeInfo() {
		return agreeInfo;
	}

	public void setAgreeInfo(String agreeInfo) {
		this.agreeInfo = agreeInfo;
	}

}
