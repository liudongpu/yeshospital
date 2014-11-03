package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class DeviceInfoInput extends RootInput {

	@ZapcomApi(value = "设备流水号", require = 1, remark = "流水号", demo = "123456")
	private String deviceSeries = "";
	@ZapcomApi(value = "设备关联手机号", require = 0, remark = "设备关联手机号", demo = "13512345678", verify = "mobile")
	private String devicePhone = "";
	public String getDeviceSeries() {
		return deviceSeries;
	}
	public void setDeviceSeries(String deviceSeries) {
		this.deviceSeries = deviceSeries;
	}
	public String getDevicePhone() {
		return devicePhone;
	}
	public void setDevicePhone(String devicePhone) {
		this.devicePhone = devicePhone;
	}

}
