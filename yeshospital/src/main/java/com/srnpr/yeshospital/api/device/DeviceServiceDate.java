package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.result.DeviceServiceResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForToken;

/**
 * 获取设备服务有效期接口
 * 
 * @author srnpr
 *
 */
public class DeviceServiceDate extends
		RootApiForToken<DeviceServiceResult, RootInput> {

	public DeviceServiceResult Process(RootInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new DeviceServiceResult();
	}

}
