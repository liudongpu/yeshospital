 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="服药信息" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
<@m_wx_init_dbcall />

<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_extend_geracomium","member_code,member_name,last_tour_order","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>




<div data-role="tabs">
  
  
  	<@m_wx_html_tab p_data=a_memberlist p_field="member_name" />
  
  	<div class="wxcss_height_1"></div>
	<#list a_memberlist as em>
		<div id="yeswx_tab_item_t_${em_index+1}" <#if em_index!=0>class="wxcss_base_none" </#if> >
		   

			<#assign a_tourlist=a_macro_wx_dbcall.queryAll("yh_tour_order_drug","","","","tour_code",em["last_tour_order"],"member_code",em["member_code"])>
			<#list a_tourlist as e>

				<div class="weui_panel">
		            <div class="weui_panel_hd">厂家：${e["manufacturer"]}</div>
		            <div class="weui_panel_bd">
		                <div class="weui_media_box weui_media_text">
		                    <h4 class="weui_media_title">药品名称：${e["drug_name"]}</h4>
		                    	用量：${e["drug_dose"]}
		                    <p class="weui_media_desc"><br/>途径：${e["drug_source"]}&nbsp;&nbsp;&nbsp;&nbsp;用法：${e["drug_usage"]}</p>
		                    
		                </div>
		            </div>
		        </div>




				
			</#list>
		   
		</div>
			
	</#list>
  
  
  	
  
  
</div>




</#if>


<@m_wx_body_end />
 
  <@m_wx_html_initjs e_js="yeswx.tab_select(1,'t')" />
<@m_wx_html_end />