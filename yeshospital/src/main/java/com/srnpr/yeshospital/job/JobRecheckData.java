package com.srnpr.yeshospital.job;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.rootweb.RootJob;

public class JobRecheckData extends RootJob {

	public void doExecute(JobExecutionContext context) {

		DbUp.upTable("yh_define").dataExec("call proc_yh_recheck_data();", new MDataMap());

		

	}

	

}
