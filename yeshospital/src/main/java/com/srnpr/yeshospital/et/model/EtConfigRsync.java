package com.srnpr.yeshospital.et.model;

public class EtConfigRsync {

	/**
	 * 请求地址
	 */
	private String url = "";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 是否需要token 默认为需要token
	 */
	private int flagToken = 1;

	public int getFlagToken() {
		return flagToken;
	}

	public void setFlagToken(int flagToken) {
		this.flagToken = flagToken;
	}

}
