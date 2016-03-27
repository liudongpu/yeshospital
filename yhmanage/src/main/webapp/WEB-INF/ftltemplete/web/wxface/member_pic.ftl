 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="照片列表" />
<@m_wx_body_begin />


<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
	<@m_wx_init_dbcall />
	
	<div class="wxcss_height_1"></div>
	<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_pic","uid,pic_url,create_time,pic_remark","-zid","","member_code",a_memberCode)>
	
	
            	<div class="wxcss_member_pic_box">
            		<#list a_memberlist as el>
            	
            			
            			
					        
					   
					   <div class="weui_panel weui_panel_access">
				            <div class="weui_panel_hd">${el["create_time"]?substring(0,10)}</div>
				            <div class="weui_panel_bd">
				                <a href="${el["pic_url"]}" class="weui_media_box weui_media_appmsg">
				                    <div class="weui_media_hd">
				                        <img class="weui_media_appmsg_thumb" src="${el["pic_url"]}" alt="">
				                    </div>
				                    <div class="weui_media_bd">
				                        <h4 class="weui_media_title">拍摄时间：${el["create_time"]}</h4>
				                        <p class="weui_media_desc">描述信息：${el["pic_remark"]}</p>
				                    </div>
				                </a>
				                
				            </div>
				            <a class="weui_panel_ft" href="${el["pic_url"]}">查看大图</a>
				        </div>
            			
            	
            		</#list>
					<@m_wx_html_msg_empty p_data=a_memberlist />
					
				</div>


<@m_wx_body_end />
 
 
<@m_wx_html_end />