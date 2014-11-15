package com.srnpr.yeshospital.api.result;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeviceOfficeResult extends RootResultWeb {

	@ZapcomApi(value = "科室编号")
	private String officeCode = "";

	@ZapcomApi(value = "科室名称")
	private String officeName = "";

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

}
