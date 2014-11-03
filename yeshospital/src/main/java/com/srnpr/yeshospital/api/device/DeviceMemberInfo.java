package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.model.DeviceMemberResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForToken;

/**
 * 获取设备关联老人信息接口
 * 
 * @author srnpr
 *
 */
public class DeviceMemberInfo extends
		RootApiForToken<DeviceMemberResult, RootInput> {

	public DeviceMemberResult Process(RootInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new DeviceMemberResult();
	}

}
