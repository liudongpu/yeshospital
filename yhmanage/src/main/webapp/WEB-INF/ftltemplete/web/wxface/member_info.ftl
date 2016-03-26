 <#include "../../macro/macro_wx.ftl" />

<@m_wx_html_begin p_title="个人信息" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
	<@m_wx_init_dbcall />
	
	<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_extend_geracomium","","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>
	
	
	
	<#if (a_memberlist?size>0) >
		<div data-role="tabs" id="tabs">
		  <div data-role="navbar">
		    <ul>
		    
		    	<#list a_memberlist as e>
					<li><a href="#t_${e_index}" data-ajax="false">${e["member_name"]}</a></li>
				</#list>
		    </ul>
		  </div>
		  
			<#list a_memberlist as e>
				<div id="t_${e_index}" class="ui-body-d ui-content">
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >老人姓名：</span>${e["member_name"]} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:yeswx.wx_bind_delete('${a_macro_wx_member_info.getSibCode()}','${e["member_code"]}')">解除绑定</a>
				   </div>
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >年龄：</span>${e["member_age"]}
				   </div>
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >身份证号码：</span>${e["card_code"]}
				   </div>
				   <div class="wxcss_show_split"></div>
				   
				   
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >老人相册：</span><a href="member_pic?u_member_code=${e["member_code"]}">点击查看</a>
				   </div>
				   
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >养老院：</span>${a_macro_wx_dbcall.dataGet("yh_geracomium_info","geracomium_name","","geracomium_code",e["geracomium_code"])  }
				   </div>
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >房间号：</span>${e["room_name"]}
				   </div>
					<div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >报销类型：</span>${a_macro_wx_dbcall.dataGet("yh_define","define_name","","define_code",e["account_type"])  }
				   </div>
					<div class="wxcss_show_split"></div>
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >既往病史：</span><br/>${e["medical_history"]}
				   </div>
				   <div class="wxcss_show_item">
				   	<span class="wxcss_show_title" >报警信息：</span><a href="member_warn?u_member_code=${e["member_code"]}">点击查看</a>
				   </div>
				</div>
					
			</#list>
		  
		  
		  	<a href="wx_bind?bind_token=${a_macro_wx_member_info.getBindToken()}" class="ui-btn">绑定其他老人</a>
		  
		  
		</div>
	<#else>
		<a href="wx_bind?bind_token=${a_macro_wx_member_info.getBindToken()}" class="ui-btn">绑定老人信息</a>
	</#if>


</#if>


<@m_wx_body_end />
 
 
<@m_wx_html_end />