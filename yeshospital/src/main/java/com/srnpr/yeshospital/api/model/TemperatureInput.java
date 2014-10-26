package com.srnpr.yeshospital.api.model;

import java.math.BigDecimal;

import com.srnpr.yeshospital.model.PostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class TemperatureInput extends PostDataInput {
	@ZapcomApi(value = "体温", remark = "体温，摄氏度", require = 1, demo = "0")
	private BigDecimal dataTemperature = BigDecimal.ZERO;

	public BigDecimal getDataTemperature() {
		return dataTemperature;
	}

	public void setDataTemperature(BigDecimal dataTemperature) {
		this.dataTemperature = dataTemperature;
	}

	
}
