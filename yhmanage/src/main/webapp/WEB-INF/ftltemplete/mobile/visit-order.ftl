 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin  p_css=["yesapp/css/yes-visit.css"] />
<@m_mobile_body_begin />

 <@m_mobile_init_dbcall />

<@m_mobile_header_begin p_title="出诊预约" />
	<@m_mobile_button_back />
	
	
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">
	${b_method.upClass("com.srnpr.yeshospital.support.AppPageSupport").upFlagOpen()}
	
	
	<#assign a_visit_code=RequestParameters['u_visit_order']?default("") >
	<#assign app_page=b_method.upClass("com.srnpr.yeshospital.support.AppPageSupport") >
	<#assign a_visit_info=b_method.upDataOne("yh_visit_order_info","","","","visit_order_code",a_visit_code) />
	
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_vo_visit_code" e_value=a_visit_code />

	<#assign b_visit_page=b_method.upControlPage("page_book_m_yh_visit_order_info","zw_f_member_code="+a_visit_info["member_code"]) />
	<@m_zapmacro_mobile_page_book b_visit_page />	
	<div class="yh_visit_split"></div>
	<#assign b_member_page=b_method.upControlPage("page_book_d_yh_member_extend_geracomium","zw_f_member_code="+a_visit_info["member_code"]) />
	<@m_zapmacro_mobile_page_book b_member_page />
	<div class="yh_visit_split"></div>
	<#assign b_sib_page=b_method.upControlPage("page_book_m_yh_sib_info","zw_f_sib_code="+a_visit_info["sib_code"]) />
	<@m_zapmacro_mobile_page_book b_sib_page />
	<div class="yh_visit_split"></div>
	
	
	<#assign a_visit_money=a_macro_mobile_dbcall.queryAll("yh_visit_money","","define_code","","hospital_code",a_visit_info["hospital_code"]) >
	<a href="#popup_nested" data-rel="popup" data-position-to="window" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-bars ui-btn-icon-left " >添加费用</a>
	<div data-role="popup" id="popup_nested" data-dismissible="false"  data-overlay-theme="a" data-theme="a" style="margin:0;min-width:300px; ">
	    <div data-role="collapsibleset"  data-content-theme="a" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" style="margin:0;">
	        
	        <div data-role="header" data-theme="a">
		    <h1>选择</h1>
		    </div>
		    <div role="main" class="ui-content">
		    
		    	<#list a_visit_money as ea>
		    	<#if ea["parent_code"]=="46580002">
			    	<div data-role="collapsible" data-inset="false">
			        <h2>${ea["define_name"]}</h2>
			            <ul data-role="listview">
			            	<#list a_visit_money as eb>
								<#if eb["parent_code"]==ea["define_code"]>
									<#--<li><a href="javascript:yesapp_visit.change_number('${eb["define_code"]}','${eb["define_name"]}','${eb["visit_money"]}','${eb["visit_unit"]}')" >${eb["define_name"]} &nbsp;&nbsp;${eb["visit_money"]}/${eb["visit_unit"]}</a></li>
										-->
									<li><a  href="javascript:yesapp_visit.change_number('${eb["define_code"]}')" >${eb["define_name"]} &nbsp;&nbsp;${eb["visit_money"]}/${eb["visit_unit"]}</a></li>

								</#if>
							</#list>

			            </ul>
			        </div>
			    	</#if>
		    	</#list>
	        
		        
		    </div><!-- /collapsible set -->
	    
	    </div><!-- /popup -->
	</div><!-- /popup -->
	
	
	
	<div data-role="popup" id="popup_money" data-dismissible="false"  data-overlay-theme="a" data-theme="a" style="margin:0;min-width:300px; ">
		<div data-role="header" data-theme="a">
	    <h1>请输入数量</h1>
	    </div>
	    <div role="main" class="ui-content">
	        <h3 class="ui-title"></h3>
	    <p>This action cannot be undone.</p>
	        <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-a" data-rel="back">确定</a>
	        
	    </div>
	</div>
	
	<ul  data-role="listview" data-inset="true"  id="yesapp_vo_table" >
		    
	</ul>
	<div class="zmcss_h_20"></div>
	处理备注：
	<textarea  id="yesapp_vo_process">
	</textarea>
	<div class="zmcss_h_20"></div>
	<a href="#" class="ui-btn ui-corner-all ui-shadow  ui-btn-a" >已出诊完成</a>
	<div class="zmcss_h_20"></div>
	<div class="zmcss_h_20"></div>
</div>
	
	
	


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-visit.js"]/>
 
 <@m_mobile_html_initjs e_js="yesapp_visit.init_visit_order()" />
 <@m_mobile_html_end />






