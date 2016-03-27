 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="心电数据" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
<@m_wx_init_dbcall />

<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_extend_geracomium","member_code,member_name,last_tour_order","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>




<div data-role="tabs" id="tabs">
  	<@m_wx_html_tab p_data=a_memberlist p_field="member_name" />
  	<div class="wxcss_height_1"></div>
  
	<#list a_memberlist as em>
		<div id="yeswx_tab_item_t_${em_index+1}" <#if em_index!=0>class="wxcss_base_none" </#if> > 
		   

			<div id="report_${em["member_code"]}" class="wxcss_data_elec_report">暂无数据</div>

			<@m_wx_html_initjs e_js="yeswx.report_elec('com_srnpr_yeshospital_api_wx_ReportElectrocardiogram','"+em["member_code"]+"')" />
		   
		</div>
			
	</#list>
  
  
  	
  
  
</div>




</#if>


<@m_wx_body_end />
 <@m_wx_html_initjs e_js="yeswx.tab_select(1,'t')" />
 
<@m_wx_html_end />