package com.srnpr.yeshospital.job;

import org.quartz.JobExecutionContext;

import com.srnpr.yeshospital.helper.SpellHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.rootweb.RootJob;

/**
 * 
 * 拼音转换类
 * 
 * @author srnpr
 *
 */
public class JobConvertSpell extends RootJob {

	public void doExecute(JobExecutionContext context) {

		
		ConvertSpell("yh_member_extend_geracomium", "member_name", "spell_info");
		ConvertSpell("yh_drug_info", "drug_name", "spell_info");
		
	}

	/**
	 * 转换
	 * 
	 * @param sTableName
	 * @param sFromField
	 * @param sToField
	 */
	public void ConvertSpell(String sTableName, String sFromField,
			String sToField) {

		for (MDataMap map : DbUp.upTable(sTableName)
				.query("zid," + sFromField, "",
						sFromField + "!='' and " + sToField + "='' ", null, 0,
						1000)) {

			map.put(sToField,
					SpellHelper.converterToFirstSpell(map.get(sFromField)).replace(" ", "")
							+ "|"
							+ SpellHelper.converterToSpell(map.get(sFromField)).replace(" ", ""));

			DbUp.upTable(sTableName).dataUpdate(map, sToField, "zid");

		}

	}

}
