package com.srnpr.yeshospital.helper;

import com.srnpr.zapcom.baseface.IBaseHelper;
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
		MMessage message = new MMessage();

		message.setMessageContent(sContent);
		message.setMessageReceive(sPhone);
		message.setSendSource("46580001000200030001");

		MessageSupport.INSTANCE.sendMessage(message);

	}

}
