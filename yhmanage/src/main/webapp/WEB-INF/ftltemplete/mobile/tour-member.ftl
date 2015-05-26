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
            	<a <@m_mobile_a_href p_page="tour-drug?u_order_code=${a_orderCode}&u_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">购买药物</a>
            	<br/>
            	
            	
            </div>

	
	<div class="zmcss_h_20"></div>
	<div  class="yb_list_box">

		<ul id="yesapp_tm_ul_drug"  data-role="listview" data-split-icon="delete" data-inset="true">
		   
		</ul>
	</div>
	
	
	
	

 </div>
 
 
 
 
 


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
  <@m_mobile_html_initjs e_js="yesapp_tour.init_tour_member()" />
 <@m_mobile_html_end />






