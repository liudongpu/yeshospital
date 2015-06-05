package com.srnpr.yeshospital.api.member;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class SuggestionInfoInput extends RootInput {

	@ZapcomApi(value = "建议类型")
	private String suggestionType = "";
	@ZapcomApi(value = "建议内容")
	private String suggestionInfo = "";

	public String getSuggestionType() {
		return suggestionType;
	}

	public void setSuggestionType(String suggestionType) {
		this.suggestionType = suggestionType;
	}

	public String getSuggestionInfo() {
		return suggestionInfo;
	}

	public void setSuggestionInfo(String suggestionInfo) {
		this.suggestionInfo = suggestionInfo;
	}

}
