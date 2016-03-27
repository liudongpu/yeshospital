 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="机构公告" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
	<@m_wx_init_dbcall />
	
	<#assign a_member_geracomium=a_macro_wx_dbcall.upOneWhere("yh_member_extend_geracomium","geracomium_code","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>
	
	<div class="wxcss_height_1"></div>
	
	<#if (a_member_geracomium?size>0) >
	
		<#assign a_geracomiuminfo=a_macro_wx_dbcall.upOneWhere("yh_geracomium_info","geracomium_name","","","geracomium_code",a_member_geracomium["geracomium_code"])>
	
		
		
		<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_geracomium_notice","","-zid","","geracomium_code",a_member_geracomium["geracomium_code"])>
		  
			<div class="wxcss_member_notice_box">
            		<#list a_memberlist as el>
            	
            			
            			
					   
					   <div class="weui_panel">
				            <div class="weui_panel_hd">${el["create_time"]}</div>
				            <div class="weui_panel_bd">
				                <div class="weui_media_box weui_media_text">
				                    	<h4 class="weui_media_title">${el["notice_name"]}</h4>
				                    	${el["notice_info"]}
				                    
				                </div>
				            </div>
				        </div>
            			
            	
            		</#list>

					
					<@m_wx_html_msg_empty p_data=a_memberlist />
				</div>
	<#else>
		<a href="wx_bind?bind_token=${a_macro_wx_member_info.getBindToken()}" class="ui-btn">绑定老人信息</a>
	</#if>


</#if>


<@m_wx_body_end />
 
 
<@m_wx_html_end />