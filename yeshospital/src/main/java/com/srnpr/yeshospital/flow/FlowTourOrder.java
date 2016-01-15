package com.srnpr.yeshospital.flow;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Store;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.support.AdviceSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.rootweb.RootFlowChange;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FlowTourOrder extends RootFlowChange {

	public MWebResult beforChange() {

		return new MWebResult();
	}

	public MWebResult afterChange() {

		// 购药前核对
		if (getFlowChange().getStatus().equals("46580001000500020002")
				|| getFlowChange().getStatus().equals("46580001000500020004")) {

			String sOrderCode = getFlowChange().getOrderCode();

			List<MDataMap> listDetails = DbUp.upTable("yh_tour_order_detail").queryByWhere("tour_code", sOrderCode);

			int iCardCount = 0;

			MDataMap mMemberMap = new MDataMap();

			AdviceSupport adviceSupport = new AdviceSupport();

			for (MDataMap map : listDetails) {
				mMemberMap.put(map.get("member_code"), "0");

				adviceSupport.createAdvice(map.get("detail_code"), map.get("member_code"), map.get("agree_info"),
						getFlowChange().getUserCode());

			}

			// 循环所有购药信息
			List<MDataMap> listDrugs = DbUp.upTable("yh_tour_order_drug").queryByWhere("tour_code", sOrderCode);
			for (MDataMap map : listDrugs) {

				// 判断是否在明细中存在该用户
				if (mMemberMap.containsKey(map.get("member_code"))) {

					// 判断是否需要代购
					if (map.get("flag_buy").equals("1")) {

						// 如果需要代购 则判断是否已校验过该用户
						if (mMemberMap.get(map.get("member_code")).equals("0")) {

							// 只有医保才需要取医保卡 计数器才开始增加
							if (StringUtils.isNotBlank(map.get("account_type")) && StringUtils
									.contains("46580001000500010002,46580001000500010003", map.get("account_type"))) {
								iCardCount++;
							}

							mMemberMap.put(map.get("member_code"), "1");

						}
					} else {

						int iLastCount = DbUp.upTable("yh_tour_order_drug").count("member_code", map.get("member_code"),
								"drug_name", map.get("drug_name"));

						// 判断该药物名称是否第一次添加 如果是第一次添加则将flag_check置为0 下次会出现小红点

						if (iLastCount <= 1) {

							map.put("flag_check", "0");

							DbUp.upTable("yh_tour_order_drug").dataUpdate(map, "flag_check", "zid");

						}

					}

				} else {
					// 如果在明细中不存在该用户，则表明该记录是多余记录，删除之
					DbUp.upTable("yh_tour_order_drug").delete("zid", map.get("zid"));
				}

			}

			// 开始更新明细表上的是否代购字段
			{

				List<String> aUpdate = new ArrayList<String>();

				// 首先将明细上的是否代购都置为0

				DbUp.upTable("yh_tour_order_detail").dataUpdate(new MDataMap("flag_buy", "0", "tour_code", sOrderCode),
						"flag_buy", "tour_code");

				// 循环单据 如果明细上有代购才更新为1
				for (String sKey : mMemberMap.keySet()) {
					if (mMemberMap.get(sKey).equals("1")) {
						MDataMap mUpdatDetailMap = new MDataMap();

						mUpdatDetailMap.inAllValues("flag_buy", "1", "tour_code", sOrderCode, "member_code", sKey);
						DbUp.upTable("yh_tour_order_detail").dataUpdate(mUpdatDetailMap, "flag_buy",
								"tour_code,member_code");

					}
				}

			}

			MDataMap mUpdateMap = new MDataMap();
			mUpdateMap.inAllValues("sum_card", String.valueOf(iCardCount), "tour_code", sOrderCode);
			DbUp.upTable("yh_tour_order_info").dataUpdate(mUpdateMap, "sum_card", "tour_code");

			// 如果用户药物信息表中为空 则根据这个单据上的信息来更新用户的服药信息
			if (DbUp.upTable("yh_member_drug").count("tour_code", sOrderCode) == 0) {

				// 删除所有的用户服药信息
				for (MDataMap map : listDetails) {

					DbUp.upTable("yh_member_drug").delete("member_code", map.get("member_code"));
				}

				MDataMap mTourOrdermDataMap = DbUp.upTable("yh_tour_order_info").one("tour_code", sOrderCode);

				// 循环所有购药信息
				List<MDataMap> listInsert = DbUp.upTable("yh_tour_order_drug").queryAll(
						"record_code,member_code,drug_code,tour_code,drug_unit,drug_name,drug_usage,create_time,manufacturer,account_type,flag_check,drug_dose,drug_single,drug_source",
						"", "", new MDataMap("tour_code", sOrderCode));
				for (MDataMap map : listInsert) {

					map.put("geracomium_code", mTourOrdermDataMap.get("geracomium_code"));

					DbUp.upTable("yh_member_drug").dataInsert(map);
				}

			}

		}

		// 购药已完成 重新计算所有的金额
		if (getFlowChange().getStatus().equals("46580001000500020005")) {

			String sOrderCode = getFlowChange().getOrderCode();

			MDataMap mUpdateMap = DbUp.upTable("yh_tour_order_detail").oneWhere(
					"ifnull(sum(money_all),0) as sum_money,ifnull(sum(money_person),0) as sum_pay", "", "", "tour_code",
					sOrderCode);
			mUpdateMap.put("tour_code", sOrderCode);
			DbUp.upTable("yh_tour_order_info").dataUpdate(mUpdateMap, "sum_money,sum_pay", "tour_code");

		}

		return new MWebResult();
	}
}
