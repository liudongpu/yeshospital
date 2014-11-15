package com.srnpr.yeshospital.api.model;

import javax.print.DocFlavor.STRING;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class SibInfo {

	@ZapcomApi(value = "亲属编号", remark = "亲属的编号", require = 0, demo = "SC001")
	private String sibCode = "";

	@ZapcomApi(value = "亲属姓名", demo = "张三")
	private String sibName = "";

	@ZapcomApi(value = "关系")
	private String sibRelation = "";

	public String getSibCode() {
		return sibCode;
	}

	public void setSibCode(String sibCode) {
		this.sibCode = sibCode;
	}

	public String getSibName() {
		return sibName;
	}

	public void setSibName(String sibName) {
		this.sibName = sibName;
	}

	public String getSibRelation() {
		return sibRelation;
	}

	public void setSibRelation(String sibRelation) {
		this.sibRelation = sibRelation;
	}
}
