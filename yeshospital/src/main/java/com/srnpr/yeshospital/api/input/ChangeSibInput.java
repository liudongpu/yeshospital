package com.srnpr.yeshospital.api.input;

import com.srnpr.yeshospital.api.model.SibInfo;
import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class ChangeSibInput extends CommonChangeTypeInput {

	@ZapcomApi(value = "亲属信息", remark = "", demo = "")
	private SibInfo sibInfo = new SibInfo();

	public SibInfo getSibInfo() {
		return sibInfo;
	}

	public void setSibInfo(SibInfo sibInfo) {
		this.sibInfo = sibInfo;
	}

}
