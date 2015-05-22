package com.srnpr.yeshospital.helper;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

public class LogHttpHelper extends BaseClass {

	public static String AddLog(String sLogType, String sUrl,
			String sRequestData) {
		String sLogCode = WebHelper.upCode("HLL");

		DbUp.upTable("yh_http_log").insert("log_code", sLogCode, "log_type",
				sLogType, "request_data", sRequestData, "log_url", sUrl,
				"request_time", FormatHelper.upDateTime());

		return sLogCode;

	}

	public static String UpdateLog(String sLogCode, String sResponseData,
			int iFlagSuccess, int iProcessNum, int iSuccessNum) {
		MDataMap mUpdateMap = new MDataMap();

		mUpdateMap.inAllValues("log_code", sLogCode, "response_data",
				sResponseData, "response_time", FormatHelper.upDateTime(),
				"flag_success", String.valueOf(iFlagSuccess), "process_num",
				String.valueOf(iProcessNum), "success_num",
				String.valueOf(iSuccessNum));

		DbUp.upTable("yh_http_log")
				.dataUpdate(
						mUpdateMap,
						"response_data,response_time,flag_success,process_num,success_num",
						"log_code");

		return sLogCode;
	}

}
