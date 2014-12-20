package com.srnpr.yeshospital.topdo;

import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapzero.server.ServerSync;

public class InitYesHospital extends RootInit {

	@Override
	public boolean onInit() {

		if (bConfig("zapzero.flag_enable_initserver").equals("0")) {
			String sGroups = "469918070008";
			return new ServerSync().initJobByGroup(sGroups);
			
		} else {
			return true;
		}

	}

	@Override
	public boolean onDestory() {

		return true;
	}

}
