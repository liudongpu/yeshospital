 <#include "../../macro/macro_wx.ftl" />

<@m_wx_html_begin p_title="照片列表" />
<@m_wx_body_begin />


<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
	<@m_wx_init_dbcall />
	<div data-role="header">
    <h1>照片列表</h1>
</div>
	
	<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_pic","uid,pic_url,create_time","-zid","","member_code",a_memberCode)>
	
	
            	<div class="wxcss_member_pic_box">
            		<#list a_memberlist as el>
            	
            			
            			<div class="wxcss_member_pic_item">
            				
            				<div class="wxcss_member_pic_info">
            					<a href="${el["pic_url"]}" target="_blank">
            					<@m_wx_html_img p_img=el["pic_url"] />
								</a>
            				</div>
            				 <div class="wxcss_member_pic_title">${el["create_time"]}</div>
            			</div>
					        
					   
					   
            			
            	
            		</#list>

					<#if (a_memberlist?size==0)>
						<div class="wxcss_member_pic_title">暂无照片</div>
					</#if>
				</div>


<@m_wx_body_end />
 
 
<@m_wx_html_end />