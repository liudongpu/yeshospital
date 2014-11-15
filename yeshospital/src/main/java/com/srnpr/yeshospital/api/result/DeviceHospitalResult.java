package com.srnpr.yeshospital.api.result;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeviceHospitalResult extends RootResultWeb {

	@ZapcomApi(value = "医院编号")
	private String hospitalCode = "";

	@ZapcomApi(value = "医院名称")
	private String hospitalName = "";

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}
