package com.srnpr.yeshospital.wx;

import com.srnpr.yeshospital.et.model.EtConfigRsync;
import com.srnpr.yeshospital.wx.model.WxConfigRsync;
import com.srnpr.yeshospital.wx.model.WxTemplateSend;
import com.srnpr.zapcom.basehelper.GsonHelper;

public class WxSendTemplate extends WxRsyncBase {

	private final static WxConfigRsync WX_CONFIG_RSYNC = new WxConfigRsync();
	static {
		WX_CONFIG_RSYNC.setUrl("message/template/send?access_token=");

	}

	public WxConfigRsync getWxConfigRsync() {

		return WX_CONFIG_RSYNC;
	}

	public String process(WxTemplateSend wxTemplateSend) {

		return upHttp(GsonHelper.toJson(wxTemplateSend));

	}

}
