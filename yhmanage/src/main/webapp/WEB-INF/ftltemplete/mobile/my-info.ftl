<#include "../macro/macro_mobile.ftl" />
<#include "../zapmacro/zapmacro_set.ftl" />



<@m_mobile_html_begin />
<@m_mobile_body_begin />


<#assign a_config=b_method.upClass("com.srnpr.yeshospital.api.app.MyInfo").upConfig() >

<#assign a_input=b_method.upClass("com.srnpr.zapweb.simpleapi.SimpleSetInput") >

${a_input.setDataCode('6ca715e600a611e596df005056165049')}


<#assign a_support=b_method.upClass("com.srnpr.zapweb.simpleapi.SimpleSetSupport") >

<#assign a_result=a_support.upResult(a_input,a_config) >

<@m_mobile_header_begin p_title="基本信息" />
<@m_mobile_header_end />

<div class="zmcss_h_20"></div>

<div>

<@m_zapmacro_set_page_show a_result />

</div>




 <@m_mobile_body_end  p_js=["yesapp/js/yesapp-my.js"]/>
 

 

 
 <@m_mobile_html_end />