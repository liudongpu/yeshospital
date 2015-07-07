package com.srnpr.yeshospital.api.wx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.srnpr.yeshospital.report.ItemAxis;
import com.srnpr.yeshospital.report.ItemChart;
import com.srnpr.yeshospital.report.ItemSeries;
import com.srnpr.yeshospital.report.ItemTitle;
import com.srnpr.yeshospital.report.ReportResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;

public class ReportPressure extends ReportBase {

	public ReportResult Process(ReportQueryInput inputParam,
			MDataMap mRequestMap) {
		ReportResult result = new ReportResult();

		ItemTitle itemTitle = new ItemTitle();
		itemTitle.setText("血压信息");

		result.setTitle(itemTitle);

		ItemChart itemChart = new ItemChart();
		itemChart.setType("line");
		result.setChart(itemChart);

		List<String> listXname = new ArrayList<String>();
		for (int i = 0; i < 31; i++) {
			listXname.add(String.valueOf(i));
		}
		ItemAxis xAxis = new ItemAxis();
		xAxis.setCategories(listXname);
		result.setxAxis(xAxis);
		
		
		ItemAxis yAxis = new ItemAxis();
		ItemTitle yTitle=new ItemTitle();
		yTitle.setText("");
		yAxis.setTitle(yTitle);
		result.setyAxis(yAxis);
		
		ItemSeries itemSeries=new ItemSeries();
		itemSeries.setName("血压");
		for (int i = 0; i < 15; i++) {
			itemSeries.getData().add(Double.valueOf(new Random().nextInt(9)));
		}
		result.getSeries().add(itemSeries);
		
		
		
		
		

		return result;
	}

}
