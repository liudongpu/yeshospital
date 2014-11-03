package com.srnpr.yeshospital.api.result;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.yeshospital.api.device.DeviceServiceDate;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeviceServiceResult extends RootResultWeb {

	@ZapcomApi(value = "服务及到期列表")
	private List<DeviceServiceDate> serviceDates = new ArrayList<DeviceServiceDate>();

	public List<DeviceServiceDate> getServiceDates() {
		return serviceDates;
	}

	public void setServiceDates(List<DeviceServiceDate> serviceDates) {
		this.serviceDates = serviceDates;
	}

}
