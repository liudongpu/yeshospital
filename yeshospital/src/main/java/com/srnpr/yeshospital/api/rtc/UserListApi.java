package com.srnpr.yeshospital.api.rtc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.GsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebTemp;

public class UserListApi extends RootApi<UserListApiResult, UserListApiInput> {

	public UserListApiResult Process(UserListApiInput inputParam, MDataMap mRequestMap) {
		UserListApiResult result = new UserListApiResult();

		Map<String, Map<String, Object>> maps = new HashMap<String, Map<String, Object>>();

		if (StringUtils.isNotBlank(inputParam.getInputJson())) {
			maps = new GsonHelper().fromJson(inputParam.getInputJson(), maps);

		}
		
		String sServerString="voipserverapp,voipserverweb,webbrowser";

		// 开始处理map内容
		if (result.upFlagTrue()) {
			for (String sKey : maps.keySet()) {

				Map<String, Object> map = maps.get(sKey);

				String sUserCode = upMapValue(map, "userCode");

				String sClientType = upMapValue(map, "clientType");

				if (StringUtils.isNotBlank(sUserCode) && StringUtils.isBlank(upMapValue(map, "showName"))) {
					// map.put("userName", "aaa");
					if (!StringUtils.contains(sServerString, sClientType)) {
						MDataMap mUserMap = DbUp.upTable("za_userinfo").one("user_name", sUserCode);

						if (mUserMap != null && !mUserMap.isEmpty()) {

							map.put("showName", mUserMap.get("real_name"));

							String sManageCode = mUserMap.get("manage_code");

							if (StringUtils.isNotBlank(sManageCode) && sManageCode.startsWith("GC")) {

								String sGeraName = DbUp.upTable("yh_geracomium_info")
										.one("geracomium_code", sManageCode).get("geracomium_name");

								map.put("showName", mUserMap.get("real_name") + "-" + sGeraName);

							}

						}
					}
				}

				if (StringUtils.isNotBlank(sUserCode) &&StringUtils.contains(sServerString, sClientType) ) {

					if (StringUtils.isBlank(upMapValue(map, "userGroup"))) {

						MDataMap mUserMap = DbUp.upTable("za_userinfo").one("user_name", sUserCode);
						if (mUserMap != null && !mUserMap.isEmpty()) {

							if (StringUtils.isBlank(upMapValue(map, "showName"))) {
								map.put("showName", mUserMap.get("real_name"));
							}

							MDataMap mDoctorMap = DbUp.upTable("yh_doctor_info").one("user_code",
									mUserMap.get("user_code"));

							if (mDoctorMap != null && !mDoctorMap.isEmpty()) {
								String sOfficeCode = mDoctorMap.get("office_code");

								if (StringUtils.isNotBlank(sOfficeCode)) {

									String sOfficeName = DbUp.upTable("yh_office_info").one("office_code", sOfficeCode)
											.get("office_name");

									map.put("userGroup", sOfficeName);

								}
							}
						}
					}

				}

			}

		}

		result.setOutJson(GsonHelper.toJson(maps));

		return result;
	}

	private String upMapValue(Map<String, Object> map, String sKey) {
		String sReturn = "";

		if (map.containsKey(sKey) && map.get(sKey) != null) {
			sReturn = map.get(sKey).toString();
		}

		return sReturn;
	}

}
