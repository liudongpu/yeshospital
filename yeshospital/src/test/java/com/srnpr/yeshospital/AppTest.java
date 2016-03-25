package com.srnpr.yeshospital;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srnpr.yeshospital.et.rsync.*;
import com.srnpr.yeshospital.job.JobRecheckAge;
import com.srnpr.yeshospital.model.TemplateForMsg;
import com.srnpr.yeshospital.support.ApicloudSupport;
import com.srnpr.yeshospital.support.DocSupport;
import com.srnpr.yeshospital.support.MsgSupport;
import com.srnpr.yeshospital.support.QrcodeSupport;
import com.srnpr.yeshospital.wx.WxSendTemplate;
import com.srnpr.yeshospital.wx.model.WxTemplageValue;
import com.srnpr.yeshospital.wx.model.WxTemplateSend;
import com.srnpr.zapcom.basehelper.EncodeHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.GsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

/**
 * Unit test for simple App.
 */
public class AppTest

{

	
}
