package com.srnpr.yeshospital.api.wx;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.srnpr.yeshospital.report.ItemAxis;
import com.srnpr.yeshospital.report.ItemChart;
import com.srnpr.yeshospital.report.ItemSeries;
import com.srnpr.yeshospital.report.ItemTitle;
import com.srnpr.yeshospital.report.ReportResult;
import com.srnpr.yeshospital.topdo.YhConst;
import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;

public class ReportPressure extends ReportBase {

	public ReportResult Process(ReportQueryInput inputParam, MDataMap mRequestMap) {
		ReportResult result = initResult();

		result.getTitle().setText("血压信息");

		ItemSeries itemSeries = new ItemSeries();
		itemSeries.setName("收缩压");

		ItemSeries itemSeries2 = new ItemSeries();
		itemSeries2.setName("舒张压");
		/*
		 * for (int i = 0; i < 15; i++) {
		 * itemSeries.getData().add(Double.valueOf(new Random().nextInt(9))); }
		 * result.getSeries().add(itemSeries);
		 */

		MDataMap mQueryMap = new MDataMap();
		mQueryMap.inAllValues("member_code", inputParam.getMemberCode());

		for (MDataMap map : DbUp.upTable("yh_post_pressure").query("create_time,upper_pressure,lower_pressure",
				"create_time", "member_code=:member_code", mQueryMap, 0, YhConst.REPORT_MAX_SIZE)) {

			BigDecimal dTime = new BigDecimal(DateHelper.parseDate(map.get("create_time")).getTime());

			itemSeries.getData().add(new BigDecimal[] { dTime, new BigDecimal(map.get("upper_pressure")) });
			itemSeries2.getData().add(new BigDecimal[] { dTime, new BigDecimal(map.get("lower_pressure")) });

		}
		result.getSeries().add(itemSeries);

		result.getSeries().add(itemSeries2);

		/*
		 * ItemSeries itemSeries2=new ItemSeries(); itemSeries2.setName("舒张压");
		 * for (int i = 0; i < 15; i++) {
		 * itemSeries2.getData().add(Double.valueOf(new Random().nextInt(50)));
		 * } result.getSeries().add(itemSeries2);
		 */

		return result;
	}

}
