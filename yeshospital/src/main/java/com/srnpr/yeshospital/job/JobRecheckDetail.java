package com.srnpr.yeshospital.job;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.rootweb.RootJob;

public class JobRecheckDetail extends RootJob {

	public void doExecute(JobExecutionContext context) {
		recheckDetail();

	}

	private void recheckDetail() {

		for (MDataMap map : DbUp.upTable("yh_tour_order_detail").queryAll("zid,create_time,member_code,create_user",
				"create_time", "date_week='' and create_time!='' ", new MDataMap())) {

			Date dt = DateHelper.parseDate(map.get("create_time"));

			String sWeeok = String.valueOf(getYearOfDate(dt))
					+ StringUtils.leftPad(String.valueOf(getWeekOfYear(dt)), 2, "0");

			if (DbUp.upTable("yh_tour_order_detail").count("member_code", map.get("member_code"), "date_week", sWeeok,
					"create_user", map.get("create_user")) > 0) {
				sWeeok = "-" + sWeeok;
			}

			map.put("date_week", sWeeok);

			DbUp.upTable("yh_tour_order_detail").dataUpdate(map, "date_week", "zid");

		}

	}

	public static Integer getYearOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Integer getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		int mouth = calendar.get(Calendar.MONTH);
		// JDK think 2015-12-31 as 2016 1th week
		// 如果月份是12月，且求出来的周数是第一周，说明该日期实质上是这一年的第53周，也是下一年的第一周
		if (mouth >= 11 && week <= 1) {
			week += 52;
		}
		return week;
	}
}
