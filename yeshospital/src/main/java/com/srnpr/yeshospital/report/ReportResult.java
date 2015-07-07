package com.srnpr.yeshospital.report;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class ReportResult extends RootResultWeb {

	private ItemChart chart = new ItemChart();

	private ItemTitle title = new ItemTitle();

	private ItemAxis xAxis = new ItemAxis();
	
	private ItemAxis yAxis = new ItemAxis();

	private List<ItemSeries> series = new ArrayList<ItemSeries>();

	public ItemTitle getTitle() {
		return title;
	}

	public void setTitle(ItemTitle title) {
		this.title = title;
	}

	public ItemAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(ItemAxis xAxis) {
		this.xAxis = xAxis;
	}

	public List<ItemSeries> getSeries() {
		return series;
	}

	public void setSeries(List<ItemSeries> series) {
		this.series = series;
	}

	public ItemChart getChart() {
		return chart;
	}

	public void setChart(ItemChart chart) {
		this.chart = chart;
	}

	public ItemAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(ItemAxis yAxis) {
		this.yAxis = yAxis;
	}

}
