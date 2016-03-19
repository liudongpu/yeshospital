 <#include "../../macro/macro_wx.ftl" />

<@m_wx_html_begin p_title="预约信息" />

<@m_wx_html_css ["mlib/mobiscroll/css/mobiscroll.custom-2.16.1.min.css"] />
	<@m_wx_html_js ["mlib/mobiscroll/js/mobiscroll.custom-2.16.1.min.js"] />

<@m_wx_body_begin />


	<#assign a_macro_wx_page_info=b_method.upClass("com.srnpr.yeshospital.wx.WxPageInfo") />

	<#assign a_macro_wx_member_info=a_macro_wx_page_info.upMemberInfo() />

	



<@m_wx_init_dbcall />

<#assign a_relationdeep=a_macro_wx_dbcall.queryAll("yh_define","define_code as v,define_name as t ",""," parent_code='4658000100020005' ")>
	
<#assign a_visittime=a_macro_wx_dbcall.queryAll("yh_define","define_code as v,define_name as t ",""," parent_code='4658000100020012' ")>


<#assign a_membersex=a_macro_wx_dbcall.queryAll("yh_define","define_code as v,define_name as t ",""," parent_code='4658000100020001' ")>

<#assign a_hospitalcode=a_macro_wx_dbcall.queryAll("yh_hospital_info","hospital_code as v,hospital_name as t ",""," hospital_service='46580001000400030002' ")>

<#macro m_visit_order_form e_index e_info e_sib>

	<div id="t_${e_index}" class="ui-body-d ui-content">
		<@m_visit_order_text p_label="预约医院" p_name="hospital_code_"+e_index  p_text=""  p_type="t_hospitalcode"/>
		
		<div class="wxcss_form_item">
			<div class="wxcss_form_label">
				预约日期：
			</div>
			<div class="wxcss_form_info">
				
				<@m_wx_html_text p_id="visit_date_"+e_index  p_value=.now?date />
				<@m_wx_html_script "$(function () {$('#visit_date_${e_index}').mobiscroll().calendar({lang: 'zh',dateFormat: 'yy-mm-dd',  display: 'bottom', controls: ['calendar', 'date']});});" />
	
				
			</div>
			<div class="wxcss_form_split"></div>
		</div>
		
		
		
		<@m_visit_order_text p_label="预约时间" p_name="visit_time_"+e_index  p_text=""  p_type="t_visittime"/>
		
		<@m_visit_order_text p_label="预约需求" p_name="visit_note_"+e_index  p_text=""  p_type="tarea"/>
		
		
		<@m_visit_order_text p_label="老人姓名" p_name="member_name_"+e_index  p_text=e_info["member_name"] />
		<@m_visit_order_text p_label="老人年龄" p_name="member_age_"+e_index  p_text=e_info["member_age"] />
		<@m_visit_order_text p_label="身份证号" p_name="card_code_"+e_index  p_text=e_info["card_code"] />
		<@m_visit_order_text p_label="老人性别" p_name="member_sex_"+e_index  p_text=e_info["member_sex"] p_type="t_membersex"/>
		
		<@m_visit_order_text p_label="老人住址" p_name="room_name_"+e_index  p_text=e_info["member_age"]  p_type="tarea" />
		<@m_visit_order_text p_label="老人电话" p_name="member_phone_"+e_index  p_text=e_info["member_phone"] />
		
		
		
		<@m_visit_order_text p_label="家属姓名" p_name="sib_name_"+e_index  p_text=e_sib["sib_name"] />
		<@m_visit_order_text p_label="家属关系" p_name="relation_deep_"+e_index  p_text=e_sib["relation_deep"] p_type="t_relationdeep" />
		<@m_visit_order_text p_label="家属电话" p_name="sib_phone_"+e_index  p_text=e_sib["mobile_phone"] />
		
		<@m_wx_html_hidden p_id="sib_code_"+e_index p_value=e_sib["sib_code"] />
		<@m_wx_html_hidden p_id="bind_token_"+e_index p_value=a_macro_wx_member_info.getBindToken() />
		
		
		<div class="wxcss_height_2"></div>
		<a href="javascript:yeswx.visit_order_submit(${e_index})" class="weui_btn weui_btn_warn">立即预约</a>
		<div class="wxcss_height_2"></div>
	</div>
</#macro>

<#macro m_visit_order_text p_label="" p_name="" p_text="" p_type="text" >


	<div class="wxcss_form_item">
		<div class="wxcss_form_label">
			${p_label}：
		</div>
		<div class="wxcss_form_info">
		
			<#if p_type=="tarea">
				<@m_wx_html_tarea p_id=p_name  p_value=p_text  />
			<#elseif p_type=="t_relationdeep">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_relationdeep p_label="请选择" />
			<#elseif p_type=="t_visittime">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_visittime p_label="" />
			<#elseif p_type=="t_membersex">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_membersex p_label="" />
			<#elseif p_type=="t_hospitalcode">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_hospitalcode p_label="" />
			<#else>
				<@m_wx_html_text p_id=p_name  p_value=p_text  />
			</#if>
			
		</div>
		<div class="wxcss_form_split"></div>
	</div>
</#macro>



<#if a_macro_wx_member_info.getAccessToken()!="">
	
	
	<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_extend_geracomium","","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>
	
	
	
	
	
	<#if (a_memberlist?size>0) >
		<div data-role="tabs" id="tabs">
		  <div data-role="navbar">
		    <ul>
		    
		    	<#list a_memberlist as e>
					<li><a href="#t_${e_index+1}" data-ajax="false">${e["member_name"]}</a></li>
				</#list>
		    </ul>
		  </div>
		  
			<#list a_memberlist as e>
				
				<#assign a_sibinfo=a_macro_wx_dbcall.upOne("yh_sib_info","sib_code",a_macro_wx_member_info.getSibCode(),"member_code",e["member_code"])>
	
				<@m_visit_order_form e_index+1 e a_sibinfo/>

				
			</#list>
		  	
		</div>
	<#else>
		<@m_visit_order_form 0 {} {}/>
	</#if>

<#else>
		<@m_visit_order_form 0 {} {}/>
</#if>



<div data-role="popup" id="visit_order_popup" data-overlay-theme="b" data-theme="b" data-dismissible="false">
  <div data-role="header" data-theme="a">
    <h1>提示消息</h1>
    </div>
    <div role="main" class="ui-content">
       
    <p>This action cannot be undone.</p>
        <a href="javascript:yeswx.wx_close()" class="ui-btn  " >确定</a>
        
    </div>
</div>



<@m_wx_body_end />
 
 
<@m_wx_html_end />