package com.srnpr.yeshospital.face;

import com.srnpr.yeshospital.api.model.ElectrocardiogramStructInput;
import com.srnpr.zapcom.baseface.IBaseApi;
import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapweb.webmodel.MWebResult;

public interface IPostDataApi<TResult extends IBaseResult, TInput extends IBaseInput>
		extends IBaseApi<TResult, TInput> {

	/**
	 * 获取逻辑处理结果
	 * 
	 * @param tInput
	 * @param sLogCode
	 * @param sManageCode
	 * @return
	 */
	public TResult upResult(TInput tInput, String sLogCode, String sManageCode);

	/**
	 * api处理方法
	 * 
	 * @param tInput
	 * @param sLogCode
	 * @param sManageCode
	 * @return
	 */
	public TResult toPost(TInput tInput, String sLogCode, String sManageCode);

	/**
	 * api插入完成后执行的方法 一般是报警之类的信息处理
	 * 
	 * @param tInput
	 * @return
	 */
	public MWebResult toProcess(TInput tInput);

}
