package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class MemberPicInput extends RootInput {

	@ZapcomApi(value = "用户编号")
	private String memberCode = "";
	@ZapcomApi(value = "创建人")
	private String createCode = "";
	@ZapcomApi(value = "图片")
	private String picImg = "";
	@ZapcomApi(value = "删除编号")
	private String deleteUid = "";

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}

	public String getDeleteUid() {
		return deleteUid;
	}

	public void setDeleteUid(String deleteUid) {
		this.deleteUid = deleteUid;
	}

}
