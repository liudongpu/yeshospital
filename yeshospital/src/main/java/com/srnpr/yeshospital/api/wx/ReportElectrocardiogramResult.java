package com.srnpr.yeshospital.api.wx;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class ReportElectrocardiogramResult extends RootResultWeb {

	@ZapcomApi(value = "明细")
	private List<ReportElectrocardiogramItem> items = new ArrayList<ReportElectrocardiogramItem>();

	public List<ReportElectrocardiogramItem> getItems() {
		return items;
	}

	public void setItems(List<ReportElectrocardiogramItem> items) {
		this.items = items;
	}

}
