package com.srnpr.yeshospital.wx.model;

public class WxTemplageValue {

	public WxTemplageValue(String sValue)
	{
		value=sValue;
	}
	
	private String value="";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
