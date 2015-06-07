package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class MemberLastInput extends RootInput {

	@ZapcomApi(value = "卡号",require=1)
	private String postCard = "";

	public String getPostCard() {
		return postCard;
	}

	public void setPostCard(String postCard) {
		this.postCard = postCard;
	}

	@ZapcomApi(value = "单据编号", remark = "  默认为空  为空时会找到该用户所在养老院的最近一张单子")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	private String orderCode = "";

}
