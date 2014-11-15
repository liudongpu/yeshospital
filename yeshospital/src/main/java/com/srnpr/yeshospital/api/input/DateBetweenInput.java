package com.srnpr.yeshospital.api.input;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class DateBetweenInput extends RootInput {

	@ZapcomApi(value = "开始时间", remark = "", demo = "2014-01-01 00:00:00")
	private String startTime = "";

	@ZapcomApi(value = "结束时间", remark = "", demo = "2014-01-01 00:00:00")
	private String endTime = "";

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
