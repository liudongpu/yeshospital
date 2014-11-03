package com.srnpr.yeshospital.api.model;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

/**
 * 子模块信息
 * 
 * @author srnpr
 *
 */
public class SubDevice {

	@ZapcomApi(value = "模块流水号", require = 1, remark = "模块流水号", demo = "123456")
	private String subSeries = "";

	@ZapcomApi(value = "模块功能", require = 1, remark = "功能的编号", demo = "1234")
	private String subProcess = "";

}
