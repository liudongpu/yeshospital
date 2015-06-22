package com.srnpr.yeshospital.api.model;

import com.srnpr.yeshospital.model.PostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class ElectrocardiogramStructInput extends PostDataInput {

	@ZapcomApi(value = "心电结果描述")
	private String structMessage = "";

	public String getStructMessage() {
		return structMessage;
	}

	public void setStructMessage(String structMessage) {
		this.structMessage = structMessage;
	}

}
