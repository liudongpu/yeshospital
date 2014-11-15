package com.srnpr.yeshospital.api.input;

import com.srnpr.yeshospital.face.IChangeType;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

public class CommonChangeTypeInput extends RootInput implements IChangeType {

	@ZapcomApi(value = "变更类型", remark = "操作类型 delete表示删除  add表示添加  update表示修改  read表示标记已读 unread表示表示未读", demo = "", verify = "in=delete,read,add,update,unread")
	private String changeType = "";

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
}
