package com.srnpr.yeshospital.job;

import org.quartz.JobExecutionContext;

import com.srnpr.yeshospital.support.ApicloudSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.MapHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.rootweb.RootJobForLock;

public class JobPushMsg extends RootJobForLock {

	public void doExecute(JobExecutionContext context) {

		// 只发送待发送且未读的消息
		for (MDataMap mMsgMap : DbUp.upTable("yh_member_msg").queryByWhere(
				"push_status", "46580001000200080001", "process_status",
				"46580001000200070001")) {

			mMsgMap.put("push_status", "46580001000200080002");
			mMsgMap.put("push_time", FormatHelper.upDateTime());

			DbUp.upTable("yh_member_msg").dataUpdate(mMsgMap,
					"push_status,push_time", "zid");

			for (MDataMap mOauthMap : DbUp.upTable("za_oauth")
					.queryByWhere("user_code", mMsgMap.get("member_code"),
							"flag_enable", "1")) {

				String sToken = mOauthMap.get("access_token");

				new ApicloudSupport().pushMessage(mMsgMap.get("msg_title"),
						mMsgMap.get("msg_info"), sToken);

			}

		}

	}

}
