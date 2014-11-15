package com.srnpr.yeshospital.api.input;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class CommonFlagInput extends RootInput {

	@ZapcomApi(value = "操作标记", remark = "0否 1 是  ", demo = "", verify = "in=0,1,2,3,4,5,6,7")
	private int operateFlag = 0;

	public int getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(int operateFlag) {
		this.operateFlag = operateFlag;
	}

}
