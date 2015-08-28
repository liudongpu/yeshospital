package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class QueryMemberInput extends RootInput {

	@ZapcomApi(value = "关键字" )
	private String keyWord = "";

	@ZapcomApi(value = "养老院编号")
	private String geracomiumCode="";
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public String getGeracomiumCode() {
		return geracomiumCode;
	}

	public void setGeracomiumCode(String geracomiumCode) {
		this.geracomiumCode = geracomiumCode;
	}

	
	

}
