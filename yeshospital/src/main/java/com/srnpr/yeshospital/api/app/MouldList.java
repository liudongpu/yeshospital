package com.srnpr.yeshospital.api.app;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class MouldList extends RootApiForToken<RootPageDataResult, MouldListInput> {

	public RootPageDataResult Process(MouldListInput inputParam, MDataMap mRequestMap) {

		String sUserCode = getUserCode();

		if (inputParam.getOptType().equals("1")) {

			String sModelType = StringUtils.defaultIfBlank(DbUp.upTable("yh_mould_info")
					.dataGet("mould_type", "", new MDataMap("model_code", inputParam.getParentCode())).toString(), "");

			DbUp.upTable("yh_mould_info").insert("model_code", WebHelper.upCode("MC"), "user_code", sUserCode,
					"mould_type", sModelType, "mould_content", inputParam.getMouldContent(), "create_time",
					DateHelper.upNow(), "parent_code", inputParam.getParentCode());

		} else if (inputParam.getOptType().equals("5")) {

			MDataMap mDataMap = new MDataMap("model_code", inputParam.getModleCode(), "mould_content",
					inputParam.getMouldContent(), "update_time", DateHelper.upNow());

			DbUp.upTable("yh_mould_info").dataUpdate(mDataMap, "mould_content,update_time", "model_code");
		} else if (inputParam.getOptType().equals("4")) {
			DbUp.upTable("yh_mould_info").delete("model_code", inputParam.getModleCode());
		}

		return new DataApiSupport().upData("yh_mould_info", "", "",
				" parent_code=:parent_code and  (user_code=:user_code or user_code='0' )",
				new MDataMap("parent_code", inputParam.getParentCode(), "user_code", sUserCode), 0, 0);

	}

}
