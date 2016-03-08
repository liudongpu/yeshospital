package com.srnpr.yeshospital.api.app;

import java.security.KeyStore.PrivateKeyEntry;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class MouldListInput extends RootInput {

	@ZapcomApi(value = "父编码")
	private String parentCode = "";
	@ZapcomApi(value = "操作编码", remark = "1 新增 5 修改 3列表 4删除")
	private String optType = "";
	@ZapcomApi(value = "内容")
	private String mouldContent = "";
	@ZapcomApi(value = "模板编号")
	private String modleCode = "";

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getMouldContent() {
		return mouldContent;
	}

	public void setMouldContent(String mouldContent) {
		this.mouldContent = mouldContent;
	}

	public String getModleCode() {
		return modleCode;
	}

	public void setModleCode(String modleCode) {
		this.modleCode = modleCode;
	}

}
