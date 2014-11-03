package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.result.DeviceProcessResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForToken;

/**设备支持功能同步接口
 * @author srnpr
 *
 */
public class DeviceProcess extends RootApiForToken<DeviceProcessResult, RootInput> {

	public DeviceProcessResult Process(RootInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new DeviceProcessResult();
	}

}
