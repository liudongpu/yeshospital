package com.srnpr.yeshospital.support;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.yeshospital.topdo.YhConst;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;

public class GridSupport {

	public List<MDataMap> upDataList(String sCode, String sMemberCode) {
		List<MDataMap> listMaps = new ArrayList<MDataMap>();

		MDataMap mQueryMap = new MDataMap("member_code", sMemberCode);

		String sTableName = "";
		String sField = "";
		String sOrder = "-create_time";

		if (sCode.equals("glucose")) {
			sTableName = "yh_post_glucose";
			sField = "left(create_time,10) as 创建时间,glucose as 血糖";
		} else if (sCode.equals("heart")) {
			sTableName = "yh_post_pressure";
			sField = "left(create_time,10) as 创建时间,heart_rate as 心率";
		} else if (sCode.equals("oxygen")) {
			sTableName = "yh_post_glucose";
			sField = "left(create_time,10) as 创建时间,oxygen as 血氧";
		} else if (sCode.equals("pressure")) {
			sTableName = "yh_post_pressure";
			sField = "left(create_time,10) as 创建时间,upper_pressure as 收缩压,lower_pressure as 舒张压";
		} else if (sCode.equals("tempeerature")) {
			sTableName = "yh_post_temperature";
			sField = "left(create_time,10) as 创建时间,temperature as 体温";
		}

		listMaps = DbUp.upTable(sTableName).query(sField, sOrder, "member_code=:member_code", mQueryMap, 0,
				YhConst.REPORT_MAX_SIZE);

		return listMaps;
	}

}
