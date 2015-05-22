package com.srnpr.yeshospital.et.rsync;

import com.srnpr.yeshospital.et.model.EtConfigRsync;

public class EtRsyncGetEntInfo extends EtRsyncBase {

	private final static EtConfigRsync ET_CONFIG_RSYNC = new EtConfigRsync();
	static {
		ET_CONFIG_RSYNC.setUrl("GetEntInfo.html");
	
	}
	public EtConfigRsync getConfigRsync() {
		
		return ET_CONFIG_RSYNC;
	}

}
