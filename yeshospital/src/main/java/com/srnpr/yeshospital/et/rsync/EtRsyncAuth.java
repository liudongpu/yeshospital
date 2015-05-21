package com.srnpr.yeshospital.et.rsync;

import com.srnpr.yeshospital.et.model.EtConfigRsync;

public class EtRsyncAuth extends EtRsyncBase {

	private final static EtConfigRsync ET_CONFIG_RSYNC = new EtConfigRsync();
	static {
		ET_CONFIG_RSYNC.setUrl("Auth.html");
		ET_CONFIG_RSYNC.setFlagToken(0);
	}

	public EtConfigRsync getConfigRsync() {
		return ET_CONFIG_RSYNC;
	}

}
