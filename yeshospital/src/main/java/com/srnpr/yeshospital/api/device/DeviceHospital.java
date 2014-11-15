package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.result.DeviceHospitalResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;

/**
 * 获取设备关联医院信息接口
 * 
 * @author srnpr
 *
 */
public class DeviceHospital extends RootApi<DeviceHospitalResult, RootInput> {

	public DeviceHospitalResult Process(RootInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new DeviceHospitalResult();
	}

}
