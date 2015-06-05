 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<@m_mobile_header_begin p_title="修改密码" />
	<@m_mobile_button_back />
	
	<a href="javascript:yesapp_my.my_password_change()" data-icon="check" class="ui-btn-right">保存</a>
	
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">

	
	
	<div class="ui-field-contain">
    
    <input name="mp_id_oldp" id="mp_id_oldp" placeholder="原密码" value="" type="password">
    <div class="zmcss_h_20"></div>
    <input name="mp_id_newp" id="mp_id_newp" placeholder="新密码" value="" type="password">
     <div class="zmcss_h_20"></div>
    <input name="mp_id_rep" id="mp_id_rep" placeholder="重复新密码" value="" type="password">
	</div>
	
	
	
	
 </div>

 <@m_mobile_body_end  p_js=["yesapp/js/yesapp-my.js"]/>
 

 <@m_mobile_html_end />






