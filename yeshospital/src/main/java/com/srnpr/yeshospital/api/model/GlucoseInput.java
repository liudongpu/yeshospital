package com.srnpr.yeshospital.api.model;

import java.math.BigDecimal;

import com.srnpr.yeshospital.model.PostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class GlucoseInput extends PostDataInput {

	@ZapcomApi(value = "血糖值", remark = "血糖值", require = 1, demo = "0")
	private BigDecimal dataGlucose = BigDecimal.ZERO;

	

	@ZapcomApi(value = "测试类型", remark = "1:空腹测试；2：餐前测试；3：餐后2小时测试；", require = 1, demo = "1")
	private String dataCheckType = "";



	public BigDecimal getDataGlucose() {
		return dataGlucose;
	}



	public void setDataGlucose(BigDecimal dataGlucose) {
		this.dataGlucose = dataGlucose;
	}



	public String getDataCheckType() {
		return dataCheckType;
	}



	public void setDataCheckType(String dataCheckType) {
		this.dataCheckType = dataCheckType;
	}

}
