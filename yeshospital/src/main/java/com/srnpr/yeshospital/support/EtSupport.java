package com.srnpr.yeshospital.support;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.et.rsync.EtRsyncAddDev;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;

public class EtSupport {

	public void bindDevice() {
		for (MDataMap map : DbUp.upTable("yh_et_gateway").queryByWhere(
				"flag_enable", "1", "flag_reg", "0")) {

			// etRsyncAddDev.
			String sRString = new EtRsyncAddDev().upHttp(new MDataMap(
					"devSerial", map.get("gateway_no")));

			map.inAllValues("reg_result", sRString, "flag_reg",
					StringUtils.equals(sRString, "succeed") ? "1" : "0");

			DbUp.upTable("yh_et_gateway").dataUpdate(map,
					"reg_result,flag_reg", "zid");

		}
	}

}
