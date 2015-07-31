package com.srnpr.yeshospital.model;

import java.math.BigDecimal;

/**
 * 监控信息
 * 
 * @author srnpr
 *
 */
public class WarnCheckInfo {

	public WarnCheckInfo() {
	};

	public WarnCheckInfo(String sType, String sSet, BigDecimal bValue,String sPostCode) {
		deviceType = sType;
		warnSet = sSet;
		deviceValue = bValue;
		postCode=sPostCode;
	};

	/**
	 * 设备类型
	 */
	private String deviceType = "";

	/**
	 * 监控内容
	 */
	private String warnSet = "";

	/**
	 * 监控值
	 */
	private BigDecimal deviceValue = BigDecimal.ZERO;

	/*
	 * 报警数据编号
	 */
	private String postCode = "";

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getWarnSet() {
		return warnSet;
	}

	public void setWarnSet(String warnSet) {
		this.warnSet = warnSet;
	}

	public BigDecimal getDeviceValue() {
		return deviceValue;
	}

	public void setDeviceValue(BigDecimal deviceValue) {
		this.deviceValue = deviceValue;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
