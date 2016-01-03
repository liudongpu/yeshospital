package com.srnpr.yeshospital.api.wx;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.yeshospital.report.ItemAxis;
import com.srnpr.yeshospital.report.ItemChart;
import com.srnpr.yeshospital.report.ReportResult;
import com.srnpr.zapcom.topapi.RootApi;

public abstract class ReportBase  extends RootApi<ReportResult, ReportQueryInput>  {

	
	
	
	
	public ReportResult initResult()
	{
		ReportResult result =new ReportResult();
		
		result.getChart().setType("spline");;
		
		
		//ItemAxis xAxis = new ItemAxis();
		
		/*
		for (int i = 0; i < 31; i++) {
			
			xAxis.getCategories().add(String.valueOf(i));
			
		}
		*/
		result.getxAxis().setType("datetime");
		
		result.getyAxis().getTitle().setText("");;
		
		return result;
	}
	
	
	
}
