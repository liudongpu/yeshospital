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
	@ZapcomApi(value = "编号")
	private String picUid = "";
	@ZapcomApi(value = "操作编号")
	private String optType = "";
	@ZapcomApi(value = "备注")
	private String picRemark = "";

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

	public String getPicUid() {
		return picUid;
	}

	public void setPicUid(String picUid) {
		this.picUid = picUid;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getPicRemark() {
		return picRemark;
	}

	public void setPicRemark(String picRemark) {
		this.picRemark = picRemark;
	}

}
