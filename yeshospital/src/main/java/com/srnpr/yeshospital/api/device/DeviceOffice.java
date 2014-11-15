package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.result.DeviceOfficeResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;

public class DeviceOffice extends RootApi<DeviceOfficeResult, RootInput> {

	public DeviceOfficeResult Process(RootInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new DeviceOfficeResult();
	}

}
