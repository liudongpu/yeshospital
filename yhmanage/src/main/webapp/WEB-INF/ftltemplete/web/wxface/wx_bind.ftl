 <#include "../../macro/macro_wx.ftl" />

<@m_wx_html_begin p_title="绑定信息" />
<@m_wx_body_begin />
<#assign a_web_helper=b_method.upClass("com.srnpr.zapweb.helper.WebSessionHelper") />

<div class="wxcss_main_width">


	<@m_wx_html_hidden p_id="wx_bind_bind_token" p_value=a_web_helper.upRequest("bind_token") />
	<div>

		

		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			请输入老人身份证：
		</div>
		<div>
			<@m_wx_html_text p_id="wx_bind_card_code"  />
		</div>
		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			请输入老人姓名：
		</div>
		<div>
			<@m_wx_html_text p_id="wx_bind_user_name"  />
		</div>
	</div>



	<a href="#" class="ui-btn">确定</a>

</div>




    
<@m_wx_body_end />
 
 
<@m_wx_html_end />