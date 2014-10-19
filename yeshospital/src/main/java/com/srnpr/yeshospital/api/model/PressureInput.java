package com.srnpr.yeshospital.api.model;

import java.math.BigDecimal;

import com.srnpr.yeshospital.model.PostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class PressureInput extends PostDataInput {

	@ZapcomApi(value = "收缩压", remark = "收缩压,上压", require = 1, demo = "0")
	private BigDecimal dataUpper = BigDecimal.ZERO;
	@ZapcomApi(value = "舒张压", remark = "舒张压,下压", require = 1, demo = "0")
	private BigDecimal dataLower = BigDecimal.ZERO;
	@ZapcomApi(value = "心率", remark = "心率", require = 0, demo = "0")
	private BigDecimal dataHeart = BigDecimal.ZERO;

	public BigDecimal getDataUpper() {
		return dataUpper;
	}

	public void setDataUpper(BigDecimal dataUpper) {
		this.dataUpper = dataUpper;
	}

	public BigDecimal getDataLower() {
		return dataLower;
	}

	public void setDataLower(BigDecimal dataLower) {
		this.dataLower = dataLower;
	}

	public BigDecimal getDataHeart() {
		return dataHeart;
	}

	public void setDataHeart(BigDecimal dataHeart) {
		this.dataHeart = dataHeart;
	}

}
