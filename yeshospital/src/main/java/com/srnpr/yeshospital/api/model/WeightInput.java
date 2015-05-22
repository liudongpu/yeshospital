package com.srnpr.yeshospital.api.model;

import java.math.BigDecimal;

import com.srnpr.yeshospital.model.PostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class WeightInput extends PostDataInput {

	@ZapcomApi(value = "体重值", remark = "体重值", require = 1, demo = "0")
	private BigDecimal weight = BigDecimal.ZERO;

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}
