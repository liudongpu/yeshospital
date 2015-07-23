package com.srnpr.yeshospital.api.model;

import com.srnpr.yeshospital.model.PostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class ElectrocardiogramImageInput extends PostDataInput {

	@ZapcomApi(value = "图片数据")
	private String imageData = "";

	@ZapcomApi(value = "图片类型", verify = "in=base64")
	private String imageType = "";

	@ZapcomApi(value = "图片链接地址")
	private String imageUrl = "";

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
