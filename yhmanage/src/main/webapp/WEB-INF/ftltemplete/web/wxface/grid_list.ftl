 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="数据信息" />

<@m_wx_body_begin />

<@m_wx_init_dbcall />
<#assign a_app_info=b_method.upClass("com.srnpr.yeshospital.support.GridSupport") >

<#assign a_member_code=RequestParameters['u_member_code']?default("") >
<#assign a_data_type=RequestParameters['u_data_type']?default("") >
<#assign a_visitlist=a_app_info.upDataList(a_data_type,a_member_code)>

	<div class="wxcss_height_1"></div>
	<#list a_visitlist as el>

		<div class="weui_cells ">
		
			<#list el?keys as ef>
			
			
			<div class="weui_cell">
		        <div class="weui_cell_bd weui_cell_primary">
		            <p> ${ef}</p>
		        </div>
		        <div class="weui_cell_ft">
		            	 ${el[ef]}
		        </div>
			</div>
			</#list>
			
		</div>
		
	</#list>

	<@m_wx_html_msg_empty p_data=a_visitlist />

<@m_wx_body_end />

<@m_wx_html_end />