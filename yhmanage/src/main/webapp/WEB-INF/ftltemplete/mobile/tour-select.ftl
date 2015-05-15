 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />




<div data-role="header" style="overflow:hidden;">
<h1>填写信息</h1>
	<a <@m_mobile_a_back/> data-icon="back" class="ui-btn-left">返回</a>
    <a href="#" data-icon="plus" class="ui-btn-right">保存</a>

	
</div><!-- /header -->



<div class="zmcss_o_a zmcss_w_96">

	
	
	<#assign tour_code=RequestParameters['tour_order']?default("") >
	<#assign app_page=b_method.upClass("com.srnpr.yeshospital.support.AppPageSupport") >
	<#assign geracomium_code=app_page.upTourOrderInfo(tour_code)["geracomium_code"]>
	

	<@m_zapmacro_mobile_html_hidden e_id="yesapp_ts_tour_code" e_value=tour_code />
	<@m_zapmacro_mobile_html_hidden e_id="yesapp_ts_geracomium_code" e_value=geracomium_code />

	<div class="zmcss_h_20"></div>

 	<input id="yesapp_ts_search" type="search" placeholder="请输入老人姓名" onkeyup="yesapp_tour.tour_select_search(this)"/>
	
	
	<ul data-role="listview" data-inset="true" id="yesapp_ts_table">
	</ul>
	
	
	
	
 </div>

 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
 
 <@m_mobile_html_end />






