package com.srnpr.yeshospital.api.notice;

import com.srnpr.yeshospital.api.result.WarnListResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForManage;

/**
 * 定时同步报警信息接口
 * 
 * @author srnpr
 *
 */
public class RsyncWarn extends RootApiForManage<WarnListResult, RootInput> {

	public WarnListResult Process(RootInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new WarnListResult();
	}

}
