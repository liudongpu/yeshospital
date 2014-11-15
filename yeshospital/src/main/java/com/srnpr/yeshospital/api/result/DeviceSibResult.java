package com.srnpr.yeshospital.api.result;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.yeshospital.api.model.SibInfo;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeviceSibResult extends RootResultWeb {

	@ZapcomApi(value = "亲属信息列表")
	private List<SibInfo> sibList = new ArrayList<SibInfo>();
	@ZapcomApi(value="亲属数量",remark="",demo="")
	private int sibCount=0;
	public List<SibInfo> getSibList() {
		return sibList;
	}
	public void setSibList(List<SibInfo> sibList) {
		this.sibList = sibList;
	}
	public int getSibCount() {
		return sibCount;
	}
	public void setSibCount(int sibCount) {
		this.sibCount = sibCount;
	}

}
