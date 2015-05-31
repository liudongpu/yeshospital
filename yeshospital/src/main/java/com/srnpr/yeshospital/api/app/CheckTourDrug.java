package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;

/**
 * 确认已服用药物
 * 
 * @author srnpr
 *
 */
public class CheckTourDrug extends
		RootApiForToken<RootResultWeb, CheckTourDrugInput> {

	public RootResultWeb Process(CheckTourDrugInput inputParam,
			MDataMap mRequestMap) {

		MDataMap mUpdateMap = new MDataMap();

		mUpdateMap.inAllValues("record_code", inputParam.getRecordCode(),
				"flag_check", "1");

		DbUp.upTable("yh_tour_order_drug").dataUpdate(mUpdateMap, "flag_check",
				"record_code");

		return new RootResultWeb();
	}
}
