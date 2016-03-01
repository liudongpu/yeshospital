package com.srnpr.yeshospital.api.visit;

import com.mchange.v2.async.StrandedTaskReporting;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class VisitOrderInput extends RootInput {

	@ZapcomApi(value = "老人姓名")
	private String memberName = "";
	@ZapcomApi(value = "老人编号", remark = "此参数优先级高")
	private String memberCode = "";
	@ZapcomApi(value = "老人年龄")
	private String memberAge = "";
	@ZapcomApi(value = "老人住址")
	private String roomName = "";
	@ZapcomApi(value = "老人电话")
	private String memberPhone = "";
	@ZapcomApi(value = "身份证号")
	private String cardCode = "";

	@ZapcomApi(value = "预约时间", require = 1)
	private String visitTime = "";

	@ZapcomApi(value = "预约需求")
	private String visitNote = "";
	@ZapcomApi(value = "老人性别")
	private String memberSex = "";
	@ZapcomApi(value = "家属编号")
	private String sibCode = "";
	@ZapcomApi(value = "家属姓名")
	private String sibName = "";
	@ZapcomApi(value = "家属关系")
	private String relationDeep = "";
	@ZapcomApi(value = "家属电话")
	private String sibPhone = "";
	@ZapcomApi(value = "医院", require = 1)
	private String hospitalCode = "";
	@ZapcomApi(value = "创建人")
	private String createUser = "";
	@ZapcomApi(value = "来源系统", verify = "in=wx,manage", remark = "微信,后台系统")
	private String sourceSystem = "";

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(String memberAge) {
		this.memberAge = memberAge;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getVisitNote() {
		return visitNote;
	}

	public void setVisitNote(String visitNote) {
		this.visitNote = visitNote;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public String getSibName() {
		return sibName;
	}

	public void setSibName(String sibName) {
		this.sibName = sibName;
	}

	public String getRelationDeep() {
		return relationDeep;
	}

	public void setRelationDeep(String relationDeep) {
		this.relationDeep = relationDeep;
	}

	public String getSibPhone() {
		return sibPhone;
	}

	public void setSibPhone(String sibPhone) {
		this.sibPhone = sibPhone;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getSibCode() {
		return sibCode;
	}

	public void setSibCode(String sibCode) {
		this.sibCode = sibCode;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
}
