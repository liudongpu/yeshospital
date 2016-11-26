package com.srnpr.yeshospital.api.census;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class CensusSubmitInput extends RootInput {

	@ZapcomApi(value = "用户编号", require = 1)
	private String memberCode;
	
	private String censusReportCode;
	
	private String optionSelect;
	
	private String reportScore;
	
	private String standCode;

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getCensusReportCode() {
		return censusReportCode;
	}

	public void setCensusReportCode(String censusReportCode) {
		this.censusReportCode = censusReportCode;
	}

	public String getOptionSelect() {
		return optionSelect;
	}

	public void setOptionSelect(String optionSelect) {
		this.optionSelect = optionSelect;
	}

	public String getReportScore() {
		return reportScore;
	}

	public void setReportScore(String reportScore) {
		this.reportScore = reportScore;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}
	
	
	
}
