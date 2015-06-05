<#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin />
<@m_mobile_body_begin />


<#assign a_code=RequestParameters['u_code']?default("") >

<#assign a_static_info=b_method.upDataOne("yh_page_static","","","","static_code",a_code) />


<@m_mobile_header_begin p_title=a_static_info["static_title"] />
<@m_mobile_header_end />


<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>

 ${a_static_info["static_info"]}

<div class="zmcss_h_20"></div>
<div class="zmcss_h_20"></div>
 </div>




 <@m_mobile_body_end />
 
 <@m_mobile_html_initjs e_js="zapjs.zw.login_out()" />
 
 <@m_mobile_html_end />