package com.srnpr.yeshospital.api.device;

import com.srnpr.yeshospital.api.input.UploadSubDeviceInput;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;

/**
 * 设备关联子模块信息上传接口
 * 
 * @author srnpr
 *
 */
public class DeviceSubInfo extends
		RootApiForToken<RootResultWeb, UploadSubDeviceInput> {

	public RootResultWeb Process(UploadSubDeviceInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new RootResultWeb();
	}

}
