 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="血压数据" />
<@m_wx_body_begin />
<@m_wx_html_js ["lib/highcharts/js/highcharts.js"] />


<@m_wx_init_dbcall />

<#assign a_memberCode=RequestParameters['u_member_code']?default("") >

<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_extend_geracomium","member_code,member_name,last_tour_order","","","member_code",a_memberCode)>




<div data-role="tabs" id="tabs">
  <@m_wx_html_tab p_data=a_memberlist p_field="member_name" />
  	<div class="wxcss_height_1"></div>
  
	<#list a_memberlist as em>
		<div id="yeswx_tab_item_t_${em_index+1}" <#if em_index!=0>class="wxcss_base_none" </#if> > 
		   <div class="weui_cells weui_cells_access">
		  		<a class="weui_cell" href="grid_list?u_data_type=pressure&u_member_code=${em["member_code"]}">
		  			<div class="weui_cell_bd weui_cell_primary">
			            <p>数据列表</p>
			        </div>
			        <div class="weui_cell_ft">
            		</div>
		   		</a>
		   	</div>
		   <br/>

			<div id="report_${em["member_code"]}" style="width:100%; height:400px;"></div>
			
			
		   
		</div>
			<@m_wx_html_initjs e_js="yeswx.report_show('com_srnpr_yeshospital_api_wx_ReportPressure','"+em["member_code"]+"')" />
	</#list>
  
  
  	
  
  
</div>






<@m_wx_body_end />
 <@m_wx_html_initjs e_js="yeswx.tab_select(1,'t')" />
 
<@m_wx_html_end />