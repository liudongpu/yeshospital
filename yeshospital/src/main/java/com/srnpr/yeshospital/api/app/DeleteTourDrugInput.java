package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class DeleteTourDrugInput extends RootInput {

	@ZapcomApi(value = "记录编号")
	private String recordCode = "";

	public String getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}

}
