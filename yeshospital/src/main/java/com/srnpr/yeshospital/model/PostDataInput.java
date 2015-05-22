package com.srnpr.yeshospital.model;

import com.srnpr.yeshospital.face.IPostDataInput;
import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.topapi.RootInput;

/**
 * 上传数据基类
 * 
 * @author srnpr
 *
 */
public class PostDataInput extends RootInput implements IPostDataInput {

	@ZapcomApi(require = 1, value = "设备流水号", remark = "设备的序列号，用以区分最终设备信息")
	private String postDecviceSerial = "";

	@ZapcomApi(require = 1, value = "测量时间", remark = "测量的时间", verify = "base=datetime", demo = "2014-01-01 00:00:00")
	private String postProcessTime = "";

	@ZapcomApi(require = 1, value = "客户端时间", remark = "客户端的当前时间", verify = "base=datetime", demo = "2014-01-01 00:00:00")
	private String postClientTime = "";

	@ZapcomApi(require = 0, value = "传输类型", remark = "设备的传输类型，decive:为终端设备(默认)，card:为刷卡", verify = "in=decive,card")
	private String postType = "";

	public String getPostDecviceSerial() {
		return postDecviceSerial;
	}

	public void setPostDecviceSerial(String postDecviceSerial) {
		this.postDecviceSerial = postDecviceSerial;
	}

	public String getPostProcessTime() {
		return postProcessTime;
	}

	public void setPostProcessTime(String postProcessTime) {
		this.postProcessTime = postProcessTime;
	}

	public String getPostClientTime() {
		return postClientTime;
	}

	public void setPostClientTime(String postClientTime) {
		this.postClientTime = postClientTime;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

}
