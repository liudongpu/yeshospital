package com.srnpr.yeshospital.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapdata.dbdo.DbUp;

public class JobRecheckData extends RootJob {

	public void doExecute(JobExecutionContext context) {

		DbUp.upTable("yh_define").dataExec("call proc_yh_recheck_data();",
				new MDataMap());

	}

}
