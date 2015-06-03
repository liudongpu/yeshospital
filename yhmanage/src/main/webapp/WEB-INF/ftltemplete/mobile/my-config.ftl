<#include "../macro/macro_mobile.ftl" />
<#include "../zapmacro/zapmacro_uset.ftl" />

<#assign a_code=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport").upConfigCode() >

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<@m_zapmacro_uset_page_html p_code=a_code  p_class="com.srnpr.yeshospital.api.app.MyConfig" p_target="my_config"/>






 <@m_mobile_body_end  p_js=["zapmobile/js/zapjs-mb.st-mb.js"]/>

 
 <@m_mobile_html_end />



