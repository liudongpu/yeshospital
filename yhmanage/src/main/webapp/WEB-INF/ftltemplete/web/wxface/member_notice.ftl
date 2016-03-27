 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="机构公告" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
	<@m_wx_init_dbcall />
	
	<#assign a_member_geracomium=a_macro_wx_dbcall.upOneWhere("yh_member_extend_geracomium","geracomium_code","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>
	
	
	
	<#if (a_member_geracomium?size>0) >
	
		<#assign a_geracomiuminfo=a_macro_wx_dbcall.upOneWhere("yh_geracomium_info","geracomium_name","","","geracomium_code",a_member_geracomium["geracomium_code"])>
	
		
		
		<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_geracomium_notice","","-zid","","geracomium_code",a_member_geracomium["geracomium_code"])>
		  
			<div class="wxcss_member_notice_box">
            		<#list a_memberlist as el>
            	
            			
            			<div class="wxcss_member_notice_item">
            				<div class="wxcss_member_notice_title">${el["notice_name"]}</div>
            				
            				<div class="wxcss_member_notice_time">${el["create_time"]}</div>
            				<div class="wxcss_member_notice_info">
            					
            					${el["notice_info"]}
								
            				</div>
            				 
            			</div>
					        
					   
					   
            			
            	
            		</#list>

					<#if (a_memberlist?size==0)>
						<div class="wxcss_base_message">暂无公告</div>
					</#if>
				</div>
	<#else>
		<a href="wx_bind?bind_token=${a_macro_wx_member_info.getBindToken()}" class="ui-btn">绑定老人信息</a>
	</#if>


</#if>


<@m_wx_body_end />
 
 
<@m_wx_html_end />