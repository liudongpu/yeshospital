
<#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin />
<@m_mobile_body_begin />

	
<div class="zmcss_h_20"></div>
<div class="yb_list_box" >
	<ul data-role="listview">
	    <li onclick="yesapp.scanner_code()"><img src="${a_macro_mobile_img_link}resources/yesapp/icons/s-1.png" alt="" class="ui-li-icon ui-corner-none"> 扫描二维码</li>
	    
	    
	</ul>
</div>
<div class="zmcss_h_10"></div>
<div class="yb_list_box" >
	<ul data-role="listview">
	    <li onclick="yesapp_frame.shake()"><img src="${a_macro_mobile_img_link}resources/yesapp/icons/s-2.png" alt="" class="ui-li-icon ui-corner-none">摇一摇</li>
	    <li><img src="${a_macro_mobile_img_link}resources/yesapp/icons/s-3.png" alt="" class="ui-li-icon ui-corner-none">历史信息</li>
	   
	    
	</ul>
</div>
 <@m_mobile_body_end  p_js=["yesapp/js/yesapp-frame.js"]/>
 <@m_mobile_html_end />
