 <#include "../../macro/macro_wx.ftl" />

<@m_wx_html_begin p_title="健康小结" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
<@m_wx_init_dbcall />

<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_extend_geracomium","member_code,member_name,last_tour_order","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>




<div data-role="tabs" id="tabs">
  <div data-role="navbar">
    <ul>
    
    	<#list a_memberlist as e>
			<li><a href="#t_${e_index}" data-ajax="false">${e["member_name"]}</a></li>
		</#list>
    </ul>
  </div>
  
	<#list a_memberlist as em>
		<div id="t_${em_index}" class="ui-body-d ui-content">
		   

			<#assign a_tourlist=a_macro_wx_dbcall.queryAll("yh_member_agree","","-create_time","","member_code",em["member_code"],"process_status","46580001000200090001")>
			<#list a_tourlist as e>

				${e["create_time"]}
				<br/><br/>
				${e["process_text"]}

				<div class="wxcss_show_split"></div>
			</#list>
		   
		</div>
			
	</#list>
  
  
  	
  
  
</div>




</#if>


<@m_wx_body_end />
 
 
<@m_wx_html_end />