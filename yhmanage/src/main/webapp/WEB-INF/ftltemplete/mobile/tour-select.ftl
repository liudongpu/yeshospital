 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<@m_mobile_header_begin p_title="巡视老人" />
	<@m_mobile_button_back />
	<#--
	<a href="javascript:yesapp_tour.tour_select_option()" data-icon="gear" class="ui-btn-right">操作</a>
	-->
<a href="javascript:yesapp_tour.tour_select_option()" data-icon="gear" class="ui-btn-right">操作</a>
	
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">
${b_method.upClass("com.srnpr.yeshospital.support.AppPageSupport").upFlagOpen()}
	
	
	<#assign a_tour_code=RequestParameters['u_tour_order']?default("") >
	<#assign app_page=b_method.upClass("com.srnpr.yeshospital.support.AppPageSupport") >
	<#assign a_geracomium_code=app_page.upTourOrderInfo(a_tour_code)["geracomium_code"]>
	

	<@m_zapmacro_mobile_form_hidden e_id="yesapp_ts_tour_code" e_value=a_tour_code />
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_ts_geracomium_code" e_value=a_geracomium_code />

	<div id="yesapp_ts_option" class="zmcss_d_none">
		<div class="zmcss_h_20"></div>
    	<a href="javascript:yesapp_tour.tour_select_finish()" class="ui-btn ui-btn-inline ui-icon-grid ui-btn-icon-left">全部已巡视完成</a>
		<a href="javascript:yesapp.scanner_code()" class="ui-btn ui-btn-inline ui-icon-camera ui-btn-icon-left">扫描二维码</a>
   		
	</div>


	<div class="zmcss_h_20"></div>
	 <div style="width:86%;" class="zmcss_f_left">
 	<input id="yesapp_ts_search" type="search" placeholder="请输入老人姓名" onkeyup="yesapp_tour.tour_select_search(this)" onkeypress="yesapp_tour.tour_select_search(this)"/>
	</div>
	<div style="width:10%;" class="zmcss_f_right">
	
	<a href="javascript:yesapp.scanner_code()" class="ui-btn ui-shadow ui-corner-all ui-icon-camera ui-btn-icon-notext">Scan</a>
   		
	</div>
	<div class="zmcss_h_10"></div>
	<div class="yb_list_box">
		<ul data-role="listview" data-inset="true" id="yesapp_ts_table">
		</ul>
	</div>
	<div class="zmcss_h_20"></div>
	
	<#--
	<div data-role="collapsible">
	<h2>查看已检查老人<span class="zmcss_f_right" id="yesapp_ts_has_count">0</span></h2>
	<div class="yb_list_box">
	    <ul data-role="listview"  id="yesapp_ts_has_check">
	       
	    </ul>
    </div>
    -->
</div>
	
	
	
	
	
 </div>

 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
 <@m_mobile_html_initjs e_js="yesapp_tour.init_tour_select()" />
 <@m_mobile_html_end />






