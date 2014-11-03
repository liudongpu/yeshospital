package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class DeviceService {

	@ZapcomApi(value = "服务编号", demo = "SC1235")
	private String serviceCode = "";
	@ZapcomApi(value = "服务名称", demo = "监控通知服务")
	private String serviceName = "";
	@ZapcomApi(value = "服务到期日期", demo = "2014-12-20 00:00:00")
	private String serviceDate = "";
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

}
