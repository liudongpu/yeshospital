package com.srnpr.yeshospital.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemSeries {

	
	private String name="";
	
	private List<BigDecimal[]> data=new ArrayList<BigDecimal[]>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BigDecimal[]> getData() {
		return data;
	}

	public void setData(List<BigDecimal[]> data) {
		this.data = data;
	}
	
	
}
