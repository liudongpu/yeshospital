package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

/**
 * 报警信息
 * 
 * @author srnpr
 *
 */
public class WarnInfo {

	@ZapcomApi(value = "报警编号", remark = "", demo = "")
	private String warnCode = "";
	@ZapcomApi(value = "报警信息", remark = "", demo = "")
	private String warnInfo = "";
	@ZapcomApi(value = "创建时间", remark = "", demo = "")
	private String createTime = "";
	@ZapcomApi(value = "创建来源", remark = "", demo = "")
	private String createSource = "";

	public String getWarnCode() {
		return warnCode;
	}

	public void setWarnCode(String warnCode) {
		this.warnCode = warnCode;
	}

	public String getWarnInfo() {
		return warnInfo;
	}

	public void setWarnInfo(String warnInfo) {
		this.warnInfo = warnInfo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateSource() {
		return createSource;
	}

	public void setCreateSource(String createSource) {
		this.createSource = createSource;
	}

}
