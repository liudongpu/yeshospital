package com.srnpr.yeshospital.api.et;

import com.srnpr.yeshospital.et.rsync.EtRsyncAddDev;
import com.srnpr.yeshospital.et.rsync.EtRsyncUpdateEnt;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class AddDevInfo extends RootApi<RootResultWeb, RootInput> {

	public RootResultWeb Process(RootInput inputParam, MDataMap mRequestMap) {

		for (MDataMap map : DbUp.upTable("yh_et_gateway").queryByWhere(
				"flag_enable", "1", "flag_reg", "0")) {

			EtRsyncAddDev etRsyncAddDev = new EtRsyncAddDev();

			// etRsyncAddDev.
			String sRString = new EtRsyncAddDev().upHttp(new MDataMap(
					"devSerial", map.get("gateway_no")));

			map.inAllValues("reg_result", sRString, "flag_reg", "1");

			DbUp.upTable("yh_et_gateway").dataUpdate(map,
					"reg_result,flag_reg", "zid");

		}

		return new RootResultWeb();
	}

}
