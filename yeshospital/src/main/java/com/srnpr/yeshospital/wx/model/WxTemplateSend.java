package com.srnpr.yeshospital.wx.model;

import java.util.HashMap;
import java.util.Map;

public class WxTemplateSend {

	private String touser = "";

	private String template_id = "";

	private Map<String, WxTemplageValue> data = new HashMap<String, WxTemplageValue>();

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public Map<String, WxTemplageValue> getData() {
		return data;
	}

	public void setData(Map<String, WxTemplageValue> data) {
		this.data = data;
	}

	

}
