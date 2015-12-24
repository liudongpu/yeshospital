 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />
<#assign a_orderCode=RequestParameters['u_order_code']?default("") >
<#assign a_memberCode=RequestParameters['u_member_code']?default("") >

<#--如果用户编号为空 则开始根据code去取该code对应的用户的最近一张单据-->
<#if a_memberCode=="">

	<#assign a_postCard=RequestParameters['u_post_card']?default("") >

	<#if a_postCard!="">
	

	</#if>


</#if>


<@m_mobile_init_dbcall />

<#assign a_uid=a_macro_mobile_dbcall.dataGet("yh_tour_order_detail","uid","","tour_code",a_orderCode,"member_code",a_memberCode) >


<#if a_uid=="" >

<#assign b_page=b_method.upControlPage("page_add_m_yh_tour_order_detail","") />

<#else>
<#assign b_page=b_method.upControlPage("page_edit_m_yh_tour_order_detail","zw_f_uid="+a_uid) />
</#if>



<@m_mobile_header_begin p_title=b_page.getWebPage().getPageName() />
	<@m_mobile_button_back />
	<@m_zapmacro_mobile_top_operate   b_page.getWebPage().getPageOperate()  "116001016" />
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>
	
	
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_tm_order_code" e_value=a_orderCode />
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_tm_member_code" e_value=a_memberCode />
	
	<#assign a_member_info=b_method.upDataOne("yh_member_extend_geracomium","","","","member_code",a_memberCode) />
	
	${a_member_info["member_name"]}-年龄：${a_member_info["member_age"]}
	<div class="zmcss_h_20"></div>

	<#if a_uid=="" >	
		<@m_zapmacro_mobile_page_add b_page />	
	<#else>
		<@m_zapmacro_mobile_page_edit b_page />
	</#if>
	

	
	

	<div class="zmcss_h_20"></div>

			<div>
            	<a <@m_mobile_a_href p_page="member-info?u_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">个人信息</a>
            	<a <@m_mobile_a_href p_page="tour-drug?u_order_code=${a_orderCode}&u_member_code=${a_memberCode}&u_drug_type=46580001000400020001" /> class="ui-btn ui-btn-inline">添加药物</a>
            	<a <@m_mobile_a_href p_page="tour-drug?u_order_code=${a_orderCode}&u_member_code=${a_memberCode}&u_drug_type=46580001000400020002" /> class="ui-btn ui-btn-inline">添加化验</a>
            	<br/>
            	<#if a_uid=="" >	
            	<br/>
            	<div class="yb_message_box">请保存完老人的查房明细/记录后再维护药物信息</div>
            	<br/>
            	</#if>
            </div>

	
	<div class="zmcss_h_20"></div>
	<div  class="yb_list_box">

		<ul id="yesapp_tm_ul_drug"  data-role="listview" data-split-icon="gear" data-inset="true">
		   
		</ul>
	</div>
	
	<div class="zmcss_h_20"></div>
	
	

 </div>
 
 

 <div data-role="popup" id="yesapp_tm_dialog_option" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1>操作</h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content" id="yesapp_tm_dialog_option_info">
       <a href="#" class="ui-btn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
       <a href="#" class="ui-btn">Anchor</a>
       
    </div>
</div>




 <div data-role="popup" id="yesapp_tm_dialog_t1" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1></h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content yb_list_dialog">
       <ol data-role="listview">
       
       		<#list b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport").upMouldInfo("46580001000200100001") as el>
       			<li onclick="yesapp_tour.tour_member_tmp_click(this)">${el}</li>
       		</#list>
		    
		    
		    
		</ol>
       
    </div>
</div>

 <div data-role="popup" id="yesapp_tm_dialog_t2" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1></h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content yb_list_dialog">
       <ol data-role="listview">
			
			<#list b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport").upMouldInfo("46580001000200100002") as el>
       			<li onclick="yesapp_tour.tour_member_tmp_click(this)">${el}</li>
       		</#list>
			
			
		    
		</ol>
       
    </div>
</div>


<div data-role="popup" id="yesapp_tm_dialog_t3" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1></h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content yb_list_dialog">
       <ol data-role="listview">
			<#list b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport").upMouldInfo("46580001000200100003") as el>
       			<li onclick="yesapp_tour.tour_member_tmp_click(this)">${el}</li>
       		</#list>
		</ol>
       
    </div>
</div>
 


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
  <@m_mobile_html_initjs e_js="yesapp_tour.init_tour_member()" />
 <@m_mobile_html_end />






