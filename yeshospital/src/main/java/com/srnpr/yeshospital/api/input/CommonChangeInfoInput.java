package com.srnpr.yeshospital.api.input;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class CommonChangeInfoInput extends CommonChangeTypeInput {

	@ZapcomApi(value = "操作编号", remark = "要操作内容的编号", demo = "WI0001")
	private String warnCode = "";

	public String getWarnCode() {
		return warnCode;
	}

	public void setWarnCode(String warnCode) {
		this.warnCode = warnCode;
	}

}
