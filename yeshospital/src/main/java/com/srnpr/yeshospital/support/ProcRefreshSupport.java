package com.srnpr.yeshospital.support;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;

public class ProcRefreshSupport {

	public void refreshCountAgree(MDataMap map) {

		DbUp.upTable("yh_define").dataExec("call proc_yh_refresh_count_agree(:tour_code,:member_code);", map);

	}

}
