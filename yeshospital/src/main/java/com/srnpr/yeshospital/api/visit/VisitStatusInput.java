package com.srnpr.yeshospital.api.visit;

import com.srnpr.zapcom.topapi.RootInput;

public class VisitStatusInput extends RootInput {

	private String visitOrderCode = "";

	public String getVisitOrderCode() {
		return visitOrderCode;
	}

	public void setVisitOrderCode(String visitOrderCode) {
		this.visitOrderCode = visitOrderCode;
	}

	private String visitProcess = "";

	public String getVisitProcess() {
		return visitProcess;
	}

	public void setVisitProcess(String visitProcess) {
		this.visitProcess = visitProcess;
	}

	private String tourInfo = "";

	private String agreeInfo = "";

	public String getTourInfo() {
		return tourInfo;
	}

	public void setTourInfo(String tourInfo) {
		this.tourInfo = tourInfo;
	}

	public String getAgreeInfo() {
		return agreeInfo;
	}

	public void setAgreeInfo(String agreeInfo) {
		this.agreeInfo = agreeInfo;
	}

}
