package com.srnpr.yeshospital.api.visit;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class VisitDetailInput extends RootInput {
	@ZapcomApi(value = "单据编号")
	private String visitOrderCode = "";
	@ZapcomApi(value = "费用编号")
	private String moneyCode = "";

	@ZapcomApi(value = "操作编码", remark = "1 新增 5 修改 3列表 4删除")
	private String optCode = "";

	@ZapcomApi(value = "数量")
	private int moneyNumber = 0;

	public String getVisitOrderCode() {
		return visitOrderCode;
	}

	public void setVisitOrderCode(String visitOrderCode) {
		this.visitOrderCode = visitOrderCode;
	}

	public String getMoneyCode() {
		return moneyCode;
	}

	public void setMoneyCode(String moneyCode) {
		this.moneyCode = moneyCode;
	}

	public String getOptCode() {
		return optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}

	public int getMoneyNumber() {
		return moneyNumber;
	}

	public void setMoneyNumber(int moneyNumber) {
		this.moneyNumber = moneyNumber;
	}

}
