package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class ReportElectrocardiogramItem {

	@ZapcomApi(value = "日期时间")
	private String dateTime = "";
	@ZapcomApi(value = "图片地址")
	private String imageUrl = "";

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
