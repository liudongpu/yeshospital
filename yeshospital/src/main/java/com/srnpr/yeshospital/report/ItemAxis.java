package com.srnpr.yeshospital.report;

import java.util.ArrayList;
import java.util.List;

public class ItemAxis {

	private List<String> categories = new ArrayList<String>();

	private ItemTitle title = new ItemTitle();

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public ItemTitle getTitle() {
		return title;
	}

	public void setTitle(ItemTitle title) {
		this.title = title;
	}

}
