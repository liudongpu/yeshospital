 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />

<@m_mobile_init_dbcall />
<#assign a_drugType=RequestParameters['u_drug_type']?default("") >

<#if a_drugType=="46580001000400020001">
	<#assign b_page=b_method.upControlPage("page_add_m_yh_tour_order_drug","") />
<#else>
	<#assign b_page=b_method.upControlPage("page_add_m_yh_tour_order_drug_c","") />
</#if>
<#assign a_orderCode=RequestParameters['u_order_code']?default("") >
<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
<@m_zapmacro_mobile_form_hidden e_id="yesapp_td_order_code" e_value=a_orderCode />
<@m_zapmacro_mobile_form_hidden e_id="yesapp_td_member_code" e_value=a_memberCode />
<@m_zapmacro_mobile_form_hidden e_id="yesapp_td_drug_type" e_value=a_drugType />

<#assign a_member_info=b_method.upDataOne("yh_member_extend_geracomium","","","","member_code",a_memberCode) />
<@m_zapmacro_mobile_form_hidden e_id="yesapp_td_account_type" e_value=a_member_info["account_type"] />

<@m_mobile_header_begin p_title=b_page.getWebPage().getPageName() />
	<@m_mobile_button_back />
	<@m_zapmacro_mobile_top_operate   b_page.getWebPage().getPageOperate()  "116001016" />
<@m_mobile_header_end />

<#assign a_uid=a_macro_mobile_dbcall.dataGet("yh_tour_order_detail","uid","","tour_code",a_orderCode,"member_code",a_memberCode) >


<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>
	${a_member_info["member_name"]}-年龄：${a_member_info["member_age"]}
	<div class="zmcss_h_20"></div>
	<#if a_uid=="" >
		<div class="yb_message_box">请保存完老人的查房明细/记录后再维护药物信息</div>
	<#else>
	
	
		<input id="yesapp_ts_search" type="search" placeholder="请输入药物名" onkeyup="yesapp_tour.tour_drug_search(this)"/>
		
		  <ul  data-role="listview" data-inset="true"  id="yesapp_ts_table" >
		    
		  </ul>
		
		
		
		<div class="zmcss_h_20"></div>
		<div id="yesapp_ts_select">
		
		<@m_zapmacro_mobile_page_add b_page />

	</#if>
	
	</div>


 </div>


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
 <@m_mobile_html_initjs e_js="yesapp_tour.init_tour_drug()" />
 <@m_mobile_html_end />






