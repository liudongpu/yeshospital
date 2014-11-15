package com.srnpr.yeshospital.api.notice;

import com.srnpr.yeshospital.api.result.NoticeResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForManage;

/**
 * 获取系统通知消息接口
 * 
 * @author srnpr
 *
 */
public class SystemNotice extends RootApiForManage<NoticeResult, RootInput> {

	public NoticeResult Process(RootInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new NoticeResult();
	}

}
