 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<#include b_page.getWebPage().getPageTemplate()?replace('zappage','zapmb')+".ftl" />


 <@m_mobile_body_end />
 
 <#if b_page.getWebPage().getSetMap()?? >
 
  <#if b_page.getWebPage().getSetMap()['ftl_initjs']?? >

 
 
 	<@m_mobile_html_js [b_page.getWebPage().getSetMap()['ftl_initjs']] />
 
 
  </#if>
 </#if>
 
 <@m_mobile_html_end />






