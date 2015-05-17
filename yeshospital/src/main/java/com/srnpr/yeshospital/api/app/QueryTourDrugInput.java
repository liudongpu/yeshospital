package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class QueryTourDrugInput extends RootInput {

	@ZapcomApi(value = "单据编号")
	private String orderCode = "";

	@ZapcomApi(value = "用户编号")
	private String memberCode = "";

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

}
