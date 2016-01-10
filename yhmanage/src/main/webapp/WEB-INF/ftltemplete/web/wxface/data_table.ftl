 <#include "../../macro/macro_wx.ftl" />
 <#include "../../zapmacro/zapmacro_mobile.ftl" />
 <#include "../../macro/macro_mobile.ftl" />
<@m_wx_html_begin p_title="" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
<@m_wx_init_dbcall />

<#assign b_method=b_method.upClass("com.srnpr.zapweb.webmethod.WebMethod")>
<#assign b_page=b_method.upControlPage("page_chart_m_yh_post_temperature","") />

<@m_zapmacro_mobile_page_chart b_page />


</#if>


<@m_wx_body_end />
 
 
<@m_wx_html_end />