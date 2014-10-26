package com.srnpr.yeshospital.api.model;

import java.math.BigDecimal;

import com.srnpr.yeshospital.model.PostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

/**
 * 血氧
 * 
 * @author srnpr
 *
 */
public class OxygenInput extends PostDataInput {
	@ZapcomApi(value = "血氧值", remark = "血氧值", require = 1, demo = "0")
	private BigDecimal dataOxygen = BigDecimal.ZERO;
	@ZapcomApi(value = "心率", remark = "心率", require = 0, demo = "0")
	private BigDecimal dataHeart = BigDecimal.ZERO;

	public BigDecimal getDataOxygen() {
		return dataOxygen;
	}

	public void setDataOxygen(BigDecimal dataOxygen) {
		this.dataOxygen = dataOxygen;
	}

	public BigDecimal getDataHeart() {
		return dataHeart;
	}

	public void setDataHeart(BigDecimal dataHeart) {
		this.dataHeart = dataHeart;
	}

}
