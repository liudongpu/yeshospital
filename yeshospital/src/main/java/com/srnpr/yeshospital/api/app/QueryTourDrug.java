package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class QueryTourDrug extends
		RootApi<RootPageDataResult, QueryTourDrugInput> {

	public RootPageDataResult Process(QueryTourDrugInput inputParam,
			MDataMap mRequestMap) {

		// 如果数据库中不存在该用户对应单据的记录 则取出该用户的服药记录或者该用户最近一条单据的记录
		if (DbUp.upTable("yh_tour_order_detail").count("tour_code",
				inputParam.getOrderCode(), "member_code",
				inputParam.getMemberCode()) == 0
				&& DbUp.upTable("yh_tour_order_drug").count("tour_code",
						inputParam.getOrderCode(), "member_code",
						inputParam.getMemberCode()) == 0) {
			MDataMap mWhereMap = new MDataMap("member_code",
					inputParam.getMemberCode());

			List<MDataMap> listMemberDrugs = DbUp
					.upTable("yh_member_drug")
					.queryAll(
							"member_code,drug_code,drug_unit,drug_name,drug_usage,create_time,manufacturer,account_type,flag_check,drug_dose,drug_single,drug_source",
							"", "", mWhereMap);

			// 这里主要是为了兼容版本 先从用户药物信息表取 如果没有则从最近一条查房单取 后续可以将从查房单取去掉
			if (listMemberDrugs != null && listMemberDrugs.size() > 0) {
				for (MDataMap map : listMemberDrugs) {
					map.inAllValues("zid", "0", "uid", WebHelper.upUuid(),
							"tour_code", inputParam.getOrderCode(),
							"create_time", FormatHelper.upDateTime(),
							"record_code", WebHelper.upCode("TOA"), "flag_buy",
							"0", "number_buy", "0");
					DbUp.upTable("yh_tour_order_drug").dataInsert(map);
				}
			} else {

				List<MDataMap> listMaps = DbUp.upTable("yh_tour_order_detail")
						.query("tour_code", "-zid", "", mWhereMap, 0, 1);
				if (listMaps != null && listMaps.size() == 1) {

					String sLastOrderCode = listMaps.get(0).get("tour_code");

					List<MDataMap> listDrugs = DbUp.upTable(
							"yh_tour_order_drug").queryByWhere("tour_code",
							sLastOrderCode, "member_code",
							inputParam.getMemberCode());

					for (MDataMap map : listDrugs) {
						map.inAllValues("zid", "0", "uid", WebHelper.upUuid(),
								"tour_code", inputParam.getOrderCode(),
								"create_time", FormatHelper.upDateTime(),
								"record_code", WebHelper.upCode("TOA"),
								"flag_buy", "0", "number_buy", "0");

						DbUp.upTable("yh_tour_order_drug").dataInsert(map);
					}

				}

			}

		}

		MDataMap mDataMap = new MDataMap("tour_code",
				inputParam.getOrderCode(), "member_code",
				inputParam.getMemberCode());

		return new DataApiSupport()
				.upData("yh_tour_order_drug",
						"uid,record_code,drug_code,drug_name,flag_buy,number_buy,flag_check,drug_unit,drug_usage,drug_dose,drug_single,drug_source,drug_type",
						"-zid", "", mDataMap, -1, -1);
	}

}
