package com.srnpr.yeshospital.helper;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseHelper;
import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MMessage;
import com.srnpr.zapweb.websupport.MessageSupport;

public class MessageHelper implements IBaseHelper {

	/**
	 * 发送消息
	 * 
	 * @param sPhone
	 * @param sContent
	 */
	public static void SendSms(String sPhone, String sContent) {

		if (StringUtils.isNotBlank(sPhone) && StringUtils.isNotBlank(sContent)) {

			MMessage message = new MMessage();

			message.setMessageContent(sContent);
			message.setMessageReceive(sPhone);
			message.setSendSource("46580001000200030001");

			MessageSupport.INSTANCE.sendMessage(message);
		}
	}

	
}
