package com.srnpr.yeshospital.api.wx;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;

public class BindCheck extends RootApi<BindCheckResult, BindCheckInput> {

	public BindCheckResult Process(BindCheckInput inputParam, MDataMap mRequestMap) {
		BindCheckResult result = new BindCheckResult();

		if (StringUtils.isBlank(inputParam.getBindToken())) {

			result.inErrorMessage(965805283);

		}

		if (result.upFlagTrue()) {

			MDataMap map = DbUp.upTable("yh_member_extend_geracomium").one("card_code", inputParam.getCardCode(),
					"member_name", inputParam.getUserName());

			if (map != null && map.size() > 0) {
				result.setMemberUid(map.get("uid"));
			} else {
				result.inErrorMessage(965805281);
			}

		}

		return result;
	}

}
