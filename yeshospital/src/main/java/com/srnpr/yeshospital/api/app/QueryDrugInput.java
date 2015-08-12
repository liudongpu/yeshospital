package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class QueryDrugInput extends RootInput {

	@ZapcomApi(value = "关键字", require = 1)
	private String keyWord = "";

	@ZapcomApi(value = "药品编号")
	private String drugCode = "";

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

}
