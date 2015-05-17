package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class DeleteTourDrug extends RootApi<RootResultWeb, DeleteTourDrugInput> {

	public RootResultWeb Process(DeleteTourDrugInput inputParam,
			MDataMap mRequestMap) {
		
		
		DbUp.upTable("yh_tour_order_drug").delete("record_code",inputParam.getRecordCode());
		
		
		return new RootResultWeb();
	}

}
