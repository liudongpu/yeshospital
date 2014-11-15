package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class NoticeInfo {

	@ZapcomApi(value = "通知编号", remark = "", demo = "")
	private String noticeCode = "";

	@ZapcomApi(value = "通知名称", remark = "", demo = "")
	private String noticeInfo = "";

	@ZapcomApi(value = "创建时间", remark = "", demo = "")
	private String createTime = "";

	@ZapcomApi(value = "更新时间", remark = "", demo = "")
	private String updateTime = "";

	@ZapcomApi(value = "是否已读", remark = "0：未读  1：已读", demo = "")
	private int readFlag = 0;

	public String getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}

}
