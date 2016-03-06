package com.srnpr.yeshospital.job;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.rootweb.RootJob;

public class JobRecheckAge extends RootJob {

	public void doExecute(JobExecutionContext context) {

		// update yeshospital.yh_member_extend_geracomium set member_age=''
		// where length(card_code) in(15,17,18);

		for (MDataMap map : DbUp.upTable("yh_member_extend_geracomium").queryAll("zid,card_code,member_sex", "",
				" member_age='' and length(card_code) in(15,17,18)", new MDataMap())) {

			map.put("member_age", upAgeFromCard(map.get("card_code")));
			DbUp.upTable("yh_member_extend_geracomium").dataUpdate(map, "member_age", "zid");

			// 更新性别定义信息
			if (StringUtils.equals(map.get("member_sex"), "46580001000200010001")
					|| StringUtils.isBlank(map.get("member_sex")))

			{
				map.put("member_sex", upAgeFromCard(map.get("card_code")));

				DbUp.upTable("yh_member_extend_geracomium").dataUpdate(map, "member_sex", "zid");

			}

		}

	}

	public String upSexFromCard(String sCard) {
		String sReturn = "46580001000200010001";

		if (sCard.length() == 15) {
			int iSex = Integer.parseInt(StringUtils.right(sCard, 1));

			sReturn = (iSex % 2 == 1) ? "46580001000200010002" : "46580001000200010003";

		} else {

			int iSex = Integer.parseInt(StringUtils.left(StringUtils.right(sCard, 2), 1));

			sReturn = (iSex % 2 == 1) ? "46580001000200010002" : "46580001000200010003";
		}

		return sReturn;
	}

	public String upAgeFromCard(String sCard) {
		String sAge = "";

		long lYearNow = Long.parseLong(StringUtils.substring(FormatHelper.upDateTime(), 0, 4));

		long lAge = 0;
		if (sCard.length() == 15) {
			lAge = Long.parseLong("19" + StringUtils.substring(sCard, 6, 8));
		} else {
			lAge = Long.parseLong(StringUtils.substring(sCard, 6, 10));
		}

		long lDeep = lYearNow - lAge;

		if (lDeep > 0 && lDeep < 200) {
			sAge = String.valueOf(lDeep);
		}

		return sAge;
	}

}
