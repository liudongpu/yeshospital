
<#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin />
<@m_mobile_body_begin />

	
<div class="zmcss_h_20"></div>
<ul data-role="listview">
    <li onclick="yesapp.scanner_code()"> 扫描二维码</li>
    <li onclick="yesapp_frame.shake()">摇一摇</li>
    <li>历史信息</li>
    
</ul>

 <@m_mobile_body_end  p_js=["yesapp/js/yesapp-frame.js"]/>
 <@m_mobile_html_end />
