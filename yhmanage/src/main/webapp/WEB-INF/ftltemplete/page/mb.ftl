 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />
 <#assign a_macro_mobile_resources_link="../" >
<@m_mobile_html_begin />
<@m_mobile_body_begin />


<#include b_page.getWebPage().getPageTemplate()?replace('zappage','zapmb')+".ftl" />

  
 <@m_mobile_body_end />
 
 <@m_mobile_html_end />






