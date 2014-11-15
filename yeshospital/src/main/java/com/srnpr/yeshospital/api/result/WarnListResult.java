package com.srnpr.yeshospital.api.result;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class WarnListResult extends RootResultWeb {

	@ZapcomApi(value="报警信息列表",remark="",demo="")
	private List<WarnListResult> warnLists=new ArrayList<WarnListResult>();

	public List<WarnListResult> getWarnLists() {
		return warnLists;
	}

	public void setWarnLists(List<WarnListResult> warnLists) {
		this.warnLists = warnLists;
	}
	
	
}
