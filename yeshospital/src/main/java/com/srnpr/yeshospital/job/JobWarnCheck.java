package com.srnpr.yeshospital.job;

import java.math.BigDecimal;

import org.quartz.JobExecutionContext;

import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.yeshospital.support.WarnSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.rootweb.RootJob;
import com.srnpr.zapweb.webmodel.MWebResult;

public class JobWarnCheck extends RootJob {

	public void doExecute(JobExecutionContext context) {

		for (MDataMap map : DbUp.upTable("yh_post_glucose").queryByWhere("flag_check", "0")) {

			// 如果是餐后测试
			if (map.get("test_type").equals("46580001000300070001")) {

				checkOne("yh_post_glucose", map, "46580001000300010005", "46580001000300030005", "glucose");
			} else {
				checkOne("yh_post_glucose", map, "46580001000300010005", "46580001000300030006", "glucose");
			}

			updateReport(map.get("member_code"), "glucose_info", bInfo(965805108, map.get("glucose")),
					"glucose_update");

		}

		for (MDataMap map : DbUp.upTable("yh_post_oxygen").queryByWhere("flag_check", "0")) {

			checkOne("yh_post_oxygen", map, "46580001000300010006", "46580001000300030003", "oxygen");
			checkOne("yh_post_oxygen", map, "46580001000300010007", "46580001000300030007", "heart_rate");

			updateReport(map.get("member_code"), "oxygen_info", bInfo(965805109, map.get("oxygen")), "oxygen_update");

		}

		for (MDataMap map : DbUp.upTable("yh_post_pressure").queryByWhere("flag_check", "0")) {

			checkOne("yh_post_pressure", map, "46580001000300010002", "46580001000300030001", "upper_pressure");
			checkOne("yh_post_pressure", map, "46580001000300010002", "46580001000300030002", "lower_pressure");
			checkOne("yh_post_pressure", map, "46580001000300010007", "46580001000300030007", "heart_rate");

			updateReport(map.get("member_code"), "pressure_info",
					bInfo(965805106, map.get("heart_rate"), map.get("lower_pressure"), map.get("upper_pressure")),
					"pressure_update");

		}

		for (MDataMap map : DbUp.upTable("yh_post_temperature").queryByWhere("flag_check", "0")) {

			checkOne("yh_post_temperature", map, "46580001000300010001", "46580001000300030004", "temperature");

			updateReport(map.get("member_code"), "temperature_info", bInfo(965805107, map.get("temperature")),
					"temperature_update");

		}

	}

	public void checkOne(String dataTable, MDataMap map, String sType, String sSet, String sField) {

		WarnSupport warnSupport = new WarnSupport();

		warnSupport.warnCheck(map.get("member_code"),
				new WarnCheckInfo(sType, sSet, new BigDecimal(map.get(sField)), map.get("post_code")));

		map.put("flag_check", "1");
		DbUp.upTable(dataTable).dataUpdate(map, "flag_check", "zid");

	}

	public MWebResult updateReport(String sMemberCode, String sInfoField, String sInfoValue, String sUpdateTimeField) {

		MDataMap mUpdateMap = DbUp.upTable("yh_report_info").one("member_code", sMemberCode);

		if (mUpdateMap == null || mUpdateMap.size() == 0) {
			mUpdateMap = new MDataMap();
			mUpdateMap.inAllValues("report_code", WebHelper.upCode("RC"), "member_code", sMemberCode);

			DbUp.upTable("yh_report_info").dataInsert(mUpdateMap);
		} else {
			mUpdateMap.inAllValues("member_code", sMemberCode);
		}

		mUpdateMap.inAllValues(sInfoField, sInfoValue, sUpdateTimeField, FormatHelper.upDateTime(), "last_update_time",
				FormatHelper.upDateTime());

		DbUp.upTable("yh_report_info").dataUpdate(mUpdateMap, sInfoField + "," + sUpdateTimeField + ",last_update_time",
				"member_code");

		return new MWebResult();

	}

}
