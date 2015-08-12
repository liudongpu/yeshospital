package com.srnpr.yeshospital.api.et;

import com.srnpr.yeshospital.et.rsync.EtRsyncAddDev;
import com.srnpr.yeshospital.et.rsync.EtRsyncUpdateEnt;
import com.srnpr.yeshospital.support.EtSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class AddDevInfo extends RootApi<RootResultWeb, RootInput> {

	public RootResultWeb Process(RootInput inputParam, MDataMap mRequestMap) {

		new EtSupport().bindDevice();

		return new RootResultWeb();
	}

}
