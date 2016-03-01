package com.srnpr.yeshospital.support;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.api.visit.VisitOrderInput;
import com.srnpr.yeshospital.topdo.YhConst;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webmodel.MWebResult;

public class VisitSupport {

	public MWebResult createVisitOrder(VisitOrderInput input) {
		MWebResult mResult = new MWebResult();

		MDataMap mMemberMap = new MDataMap();

		if (mResult.upFlagTrue()) {

			mMemberMap = inValueIfnotBlank(mMemberMap, "card_code", input.getCardCode(), "member_name",
					input.getMemberName(), "member_age", input.getMemberAge(), "room_name", input.getRoomName(),
					"member_sex", input.getMemberSex(), "member_phone", input.getMemberPhone(), "hospital_code",
					input.getHospitalCode(), "create_user", input.getCreateUser(), "geracomium_code",
					YhConst.VISIT_GERACOMIUM_CODE);

		}

		// 开始进行信息的校验检测
		if (mResult.upFlagTrue()) {

		}

		// 开始判断用户是否存在
		if (mResult.upFlagTrue()) {

			if (StringUtils.isNotBlank(input.getCardCode())) {
				MDataMap mExistMember = DbUp.upTable("yh_member_extend_geracomium").one("card_code",
						input.getCardCode());

				// 如果已存在老人信息
				if (mExistMember != null && mExistMember.size() > 0) {

					// 如果老人证件号和姓名一致时 更新老人信息
					if (mExistMember.get("member_name").equals(input.getMemberName())) {

						mMemberMap.put("member_code", mExistMember.get("member_code"));
						input.setMemberCode(mExistMember.get("member_code"));

						DbUp.upTable("yh_member_extend_geracomium").dataUpdate(mMemberMap,
								"member_age,room_name,member_sex,member_phone,hospital_code", "member_code");

					} else {
						mResult.inErrorMessage(965805301);
					}

				}

			}

		}

		// 开始处理是否需要插入用户信息表
		if (mResult.upFlagTrue()) {

			if (StringUtils.isBlank(input.getMemberCode())) {

				mMemberMap.inAllValues("member_code", WebHelper.upCode("VMI"), "create_time",
						FormatHelper.upDateTime());

				DbUp.upTable("yh_member_extend_geracomium").dataInsert(mMemberMap);
			}

		}

		// 二次格式化数据
		if (mResult.upFlagTrue()) {
			input.setMemberCode(mMemberMap.get("member_code"));
		}

		// 处理家属数据
		if (mResult.upFlagTrue()) {

			// 如果没有家属号码 则处理为本人
			if (StringUtils.isBlank(input.getSibPhone())) {
				if (StringUtils.isBlank(input.getMemberName()))
					input.setSibName(input.getMemberName());
				if (StringUtils.isBlank(input.getRelationDeep()))
					input.setRelationDeep("46580001000200050007");

				input.setSibPhone(input.getMemberPhone());
			}

			if (StringUtils.isNotBlank(input.getSibPhone())) {
				MDataMap mExistSib = DbUp.upTable("yh_sib_info").one("member_code", input.getMemberCode(),
						"mobile_phone", input.getSibPhone());

				if (mExistSib != null && mExistSib.size() > 0) {

					input.setSibCode(mExistSib.get("sib_code"));

				} else {
					MDataMap mSibMap = inValueIfnotBlank(new MDataMap(), "sib_code", WebHelper.upCode("VSC"),
							"member_code", input.getMemberCode(), "sib_name", input.getSibName(), "mobile_phone",
							input.getSibPhone(), "relation_deep", input.getRelationDeep());

					mSibMap.inAllValues("create_time", FormatHelper.upDateTime(), "create_user", input.getCreateUser());

					DbUp.upTable("yh_sib_info").dataInsert(mSibMap);

					input.setSibCode(mSibMap.get("sib_code"));
				}

			}

		}

		// 开始创建出诊单
		if (mResult.upFlagTrue()) {

			// 防止重复插入 同一时间只能有一条数据
			if (DbUp.upTable("yh_visit_order_info").count("member_code", input.getMemberCode(), "visit_time",
					input.getVisitTime()) == 0) {

				DbUp.upTable("yh_visit_order_info").insert("visit_order_code", WebHelper.upCode("VC"), "hospital_code",
						input.getHospitalCode(), "member_code", input.getMemberCode(), "visit_time",
						input.getVisitTime(), "visit_note", input.getVisitNote(), "create_time",
						FormatHelper.upDateTime(), "create_user", input.getCreateUser(), "sib_code",
						input.getSibCode());
			} else {
				mResult.inErrorMessage(965805302);
			}
		}

		return mResult;
	}

	public MDataMap inValueIfnotBlank(MDataMap mDataMap, String... strings) {

		for (int i = 0, j = strings.length; i < j; i = i + 2) {
			if (StringUtils.isNotBlank(strings[i + 1])) {
				mDataMap.put(strings[i], strings[i + 1]);
			}
		}

		return mDataMap;
	}

}
