package com.srnpr.yeshospital.job;

import org.quartz.JobExecutionContext;
import org.springframework.http.converter.FormHttpMessageConverter;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapweb.rootweb.RootJob;
import com.srnpr.zapweb.websupport.MessageSupport;

public class JobSendMessage extends RootJob {

	public void doExecute(JobExecutionContext context) {

		MessageSupport messageSupport = new MessageSupport();

		for (MDataMap mDataMap : messageSupport.upSendListBySendSource("46580001000200030001")) {

			String sMsg = bConfig("yeshospital.sms_link");

			String sSend = FormatHelper.formatString(sMsg, mDataMap.get("msg_receive"), mDataMap.get("msg_content"));
			String sReturn = "";
			try {
				sReturn = WebClientSupport.create().doGet(sSend);
			} catch (Exception e) {

				e.printStackTrace();
			}

			bLogInfo(965805206, sSend, sReturn);

		}

	}

}
