package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.model.DeviceInfoInput;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;

/**
 * 设备信息上传接口
 * 
 * @author srnpr
 *
 */
public class UpdateDeviceInfo extends
		RootApiForToken<RootResultWeb, DeviceInfoInput> {

	public RootResultWeb Process(DeviceInfoInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new RootResultWeb();
	}

}
