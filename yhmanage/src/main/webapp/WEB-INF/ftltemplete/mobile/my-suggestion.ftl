 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<@m_mobile_header_begin p_title="意见反馈" />
	<@m_mobile_button_back />
	
	<a href="javascript:yesapp_my.my_suggestion_submit()" data-icon="check" class="ui-btn-right">提交</a>
	
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">

	
	
	<div class="ui-field-contain">
    
    <textarea cols="40" rows="8" name="ms_id_suggestion" id="ms_id_suggestion"></textarea>
	</div>
	
	
	
	
 </div>

 <@m_mobile_body_end  p_js=["yesapp/js/yesapp-my.js"]/>
 

 <@m_mobile_html_end />






