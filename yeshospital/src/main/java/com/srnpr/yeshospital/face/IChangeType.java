package com.srnpr.yeshospital.face;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

public interface IChangeType {

	
	
	//@ZapcomApi(value = "变更类型", remark = "操作类型 delete表示删除  add表示添加  update表示修改  read表示标记已读 unread表示表示未读", demo = "", verify = "in=delete,read,add,update,unread")
	/**
	 * 获取修改类型
	 * 
	 * @return
	 */
	public String getChangeType();

	/**
	 * 设置修改类型
	 * 
	 * @param changeType
	 */
	public void setChangeType(String changeType);
}
