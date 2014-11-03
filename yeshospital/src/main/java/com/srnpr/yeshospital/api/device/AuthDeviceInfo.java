package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.model.DeviceAuthResult;
import com.srnpr.yeshospital.api.model.DeviceInfoInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webapi.RootApiForManage;
import com.srnpr.zapweb.webapi.RootResultWeb;

/**
 * @author srnpr 设备信息认证接口
 */
public class AuthDeviceInfo extends
		RootApiForManage<DeviceAuthResult, DeviceInfoInput> {

	public DeviceAuthResult Process(DeviceInfoInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new DeviceAuthResult();
	}

}
