 <#include "../../macro/macro_wx.ftl" />
  <#include "../../macro/macro_visit.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="预约信息" />

<@m_wx_html_css ["mlib/mobiscroll/css/mobiscroll.custom-2.16.1.min.css"] />
	<@m_wx_html_js ["mlib/mobiscroll/js/mobiscroll.custom-2.16.1.min.js","mlib/apicloud/script/api.js","zapmobile/js/zmapi.js","zapmobile/js/zmjs.js"] />

<@m_wx_body_begin />

<#assign a_member_code=RequestParameters['u_member_code']?default("") >
<#assign a_sib_code=RequestParameters['u_sib_code']?default("") >

<#assign a_macro_wx_page_info=b_method.upClass("com.srnpr.yeshospital.wx.WxPageInfo") />
<#assign a_macro_wx_member_info=a_macro_wx_page_info.upMemberInfo() />


<a href="javascript:zmjs.page.back_page()" class="weui_btn weui_btn_default">返回</a>



<div id="vo_main_page">


		<#assign a_memberinfo=a_macro_wx_dbcall.upOne("yh_member_extend_geracomium","member_code",a_member_code)>
	
		<#assign a_sibinfo=a_macro_wx_dbcall.upOne("yh_sib_info","sib_code",a_sib_code,"member_code",a_member_code)>
	
		<@m_visit_order_form 0 a_memberinfo a_sibinfo/>



</div>

<div  id="vo_main_success" class="weui_msg wxcss_base_none ">
    <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
    <div class="weui_text_area">
        <h2 class="weui_msg_title">操作成功</h2>
        <p class="weui_msg_desc">您的预约已成功，如果有问题请与客服人员联系。</p>
    </div>
    <div class="weui_opr_area">
        <p class="weui_btn_area">
            <a href="javascript:zmjs.page.back_page()" class="weui_btn weui_btn_primary">确定</a>
           
        </p>
    </div>
    <div class="weui_extra_area">
        
    </div>
</div>





<@m_wx_body_end />
 
 <@m_wx_html_initjs e_js="yeswx.tab_select(1,'t')" />
<@m_wx_html_end />