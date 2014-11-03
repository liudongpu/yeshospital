package com.srnpr.yeshospital.api.result;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeviceProcessResult extends RootResultWeb {

	@ZapcomApi(value = "设备支持的功能")
	private String process = "";

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}
