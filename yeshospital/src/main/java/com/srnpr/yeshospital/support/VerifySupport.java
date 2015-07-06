package com.srnpr.yeshospital.support;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.helper.MessageHelper;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebResult;

public class VerifySupport extends BaseClass {

	public boolean VerifySend(String sVerifyType, String sPhone, int iLength,
			String sExpiress) {
		String sCode = StringUtils.leftPad(
				String.valueOf(new Random().nextInt((int)Math.pow(10, iLength) - 1)),
				iLength, "0");
		
		

		DbUp.upTable("yh_verify_message").insert("mobile_phone", sPhone,
				"verify_code", sCode, "create_time", FormatHelper.upDateTime(),
				"verify_type", sVerifyType, "expiress_time",
				DateHelper.upDateTimeAdd(sExpiress));

		MessageHelper.SendSms(sPhone, bInfo(965805203, sCode));

		return true;

	}

	public MWebResult verifyCheck(String sVerifyType, String sPhone,
			String sCode) {

		MWebResult mWebResult = new MWebResult();

		MDataMap mDataMap = DbUp
				.upTable("yh_verify_message")
				.oneWhere(
						"",
						"-zid",
						"mobile_phone=:mobile_phone and verify_type=:verify_type and flag_verify=0 and verify_sum<60 and   expiress_time>=:expiress_time",
						"mobile_phone", sPhone, "verify_type", sVerifyType,
						"expiress_time", FormatHelper.upDateTime());

		if (mDataMap != null && mDataMap.size() > 0) {

			if (mDataMap.get("verify_code").equals(sCode)) {
				mDataMap.inAllValues("flag_verify", "1", "verify_time",
						FormatHelper.upDateTime());

				DbUp.upTable("yh_verify_message").dataUpdate(mDataMap,
						"flag_verify,verify_time", "zid");
			} else {

				mDataMap.put("verify_sum", String.valueOf(Integer
						.valueOf(mDataMap.get("verify_sum")) + 1));

				DbUp.upTable("yh_verify_message").dataUpdate(mDataMap,
						"verify_sum", "zid");
				mWebResult.inErrorMessage(965805205);
			}

		} else {
			mWebResult.inErrorMessage(965805204);
		}

		return mWebResult;
	}
}
