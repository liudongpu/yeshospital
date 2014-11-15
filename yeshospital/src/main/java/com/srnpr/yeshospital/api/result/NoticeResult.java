package com.srnpr.yeshospital.api.result;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.yeshospital.api.model.NoticeInfo;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class NoticeResult extends RootResultWeb {

	@ZapcomApi(value = "通知列表", remark = "通知的list", demo = "")
	private List<NoticeInfo> notices = new ArrayList<NoticeInfo>();

	public List<NoticeInfo> getNotices() {
		return notices;
	}

	public void setNotices(List<NoticeInfo> notices) {
		this.notices = notices;
	}

}
