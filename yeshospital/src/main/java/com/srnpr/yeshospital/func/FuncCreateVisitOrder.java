package com.srnpr.yeshospital.func;

import com.srnpr.yeshospital.api.visit.VisitOrderInput;
import com.srnpr.yeshospital.support.VisitSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.SerializeSupport;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncCreateVisitOrder extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		VisitOrderInput input = new VisitOrderInput();

		SerializeSupport<VisitOrderInput> serializeSupport = new SerializeSupport<VisitOrderInput>();
		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);
		input = serializeSupport.serialize(mAddMaps, input);

		
		MUserInfo mUserInfo=UserFactory.INSTANCE.create();
		
		input.setCreateUser(mUserInfo.getUserCode());
		input.setHospitalCode(mUserInfo.getManageCode());
		
		
		return new VisitSupport().createVisitOrder(input);
	}

}
