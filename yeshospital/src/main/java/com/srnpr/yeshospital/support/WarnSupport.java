package com.srnpr.yeshospital.support;

import java.math.BigDecimal;

import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebResult;

public class WarnSupport extends BaseClass {

	public void warnCheck(String sMemberCode, WarnCheckInfo... checkInfos) {

		MWebResult mWebResult = new MWebResult();

		for (WarnCheckInfo warnCheckInfo : checkInfos) {

			MDataMap mDataMap = DbUp
					.upTable("yh_warn_value")
					.oneWhere(
							"",
							"",
							"device_type=:device_type and warn_set=:warn_set and min_value<=:device_value and max_value>:device_value",
							"device_type", warnCheckInfo.getDeviceType(),
							"warn_set", warnCheckInfo.getWarnSet(),
							"device_value",
							warnCheckInfo.getDeviceValue().toString());
			
			if(mDataMap!=null&&mDataMap.size()>0)
			{
				
				
				
				
			}
			
			
			

		}

	}
}
