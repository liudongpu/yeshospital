<#include "../macro/macro_mobile.ftl" />
<#include "../zapmacro/zapmacro_uset.ftl" />

<#assign a_code=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport").upConfigCode() >

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<@m_zapmacro_uset_page_html p_code=a_code  p_class="com.srnpr.yeshospital.api.app.MyConfig" p_target="my_config"/>



<div class=" zmcss_w_96">
<div class="zmcss_h_20"></div>
 <button class="btn btn-positive btn-block" onclick="zapjs.zw.login_out()">退出登陆 </button>

</div>


 <@m_mobile_body_end  p_js=["zapmobile/js/zapjs-mb.st-mb.js"]/>

 
 <@m_mobile_html_end />



