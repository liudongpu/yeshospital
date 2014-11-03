package com.srnpr.yeshospital.api.input;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.yeshospital.api.model.SubDevice;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class UploadSubDeviceInput extends RootInput {

	@ZapcomApi(value = "模块列表", require = 1, remark = "模块的实体", demo = "")
	private List<SubDevice> sub = new ArrayList<SubDevice>();

	public List<SubDevice> getSub() {
		return sub;
	}

	public void setSub(List<SubDevice> sub) {
		this.sub = sub;
	}

}
