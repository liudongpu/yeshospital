 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />

<@m_mobile_init_dbcall />
<#assign a_uid=RequestParameters['zw_f_uid']?default("") >


<#assign b_page=b_method.upControlPage("page_edit_m_yh_count_warn_geracomium","zw_f_uid="+a_uid) />



<#assign a_daily_info=b_method.upDataOne("yh_count_warn_geracomium","","","","uid",a_uid) />


<@m_mobile_header_begin p_title=b_page.getWebPage().getPageName() />
	<@m_mobile_button_back />
	<@m_zapmacro_mobile_top_operate   b_page.getWebPage().getPageOperate()  "116001016" />
<@m_mobile_header_end />




<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>
	
	<a <@m_mobile_a_href p_page="member-info?u_member_code=${a_daily_info['member_code']}" /> class="ui-btn ui-btn-inline">个人信息</a>
	<div class="zmcss_h_20"></div>
	<@m_zapmacro_mobile_page_edit b_page />
	
	</div>


 </div>


 <@m_mobile_body_end />
 

 <@m_mobile_html_end />






