 <#include "../../macro/macro_wx.ftl" />

<@m_wx_html_begin p_title="购药信息" />
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
		   

			<#assign a_tourlist=a_macro_wx_dbcall.queryAll("yh_tour_order_drug","","","","tour_code",em["last_tour_order"])>
			<#list a_tourlist as e>

				<div class="wxcss_show_item">
		   			<span class="wxcss_show_title" >药品名称：${e["drug_name"]}</span>
		   		</div>
				<div class="wxcss_show_item">
		   			厂家：${e["manufacturer"]}
		   		</div>
		   		<div class="wxcss_show_item">
		   			用量：${e["drug_dose"]}
		   		</div>
		   		<div class="wxcss_show_item">
		   			途径：${e["drug_source"]}&nbsp;&nbsp;&nbsp;&nbsp;用法：${e["drug_usage"]}
		   		</div>

				<div class="wxcss_show_split"></div>
			</#list>
		   
		</div>
			
	</#list>
  
  
  	
  
  
</div>




</#if>


<@m_wx_body_end />
 
 
<@m_wx_html_end />