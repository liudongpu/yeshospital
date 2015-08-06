package com.srnpr.yeshospital.support;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebUp;

public class MemberMsgSupport {

	/*
	 * 根据外部编号更新用户消息表状态
	 */
	public boolean finishMsgByOutCode(String... sOutCodes) {

		for (String sCode : sOutCodes) {
			MDataMap mUpdateMap = new MDataMap();
			mUpdateMap.inAllValues("out_code", sCode, "process_status",
					"46580001000200070002", "process_time",
					FormatHelper.upDateTime());
			DbUp.upTable("yh_member_msg")
					.dataUpdate(mUpdateMap, "", "out_code");

		}

		return true;
	}

	public boolean createMsg(MDataMap mDataMap) {
		mDataMap.inAllValues("msg_code", WebHelper.upCode("MSGC"),
				"create_time", FormatHelper.upDateTime());

		DbUp.upTable("yh_member_msg").dataInsert(mDataMap);
		return true;
	}

}
