package com.srnpr.yeshospital.report;

import java.util.ArrayList;
import java.util.List;

public class ItemAxis {

	private List<String> categories = null;

	private ItemTitle title = new ItemTitle();
	
	private String type=null;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
