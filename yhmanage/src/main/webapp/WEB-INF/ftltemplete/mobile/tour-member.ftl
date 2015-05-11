 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />
<#assign a_orderCode=RequestParameters['order_code']?default("") >
<#assign a_memberCode=RequestParameters['member_code']?default("") >

<header class="bar bar-nav">
  <button class="btn btn-link btn-nav pull-left" <@m_mobile_back_page/> >
    <span class="icon icon-left-nav"></span>
    返回
  </button>
 
  <h1 class="title">填写信息</h1>
</header>


<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>

<@m_zapmacro_mobile_html_hidden e_id="yesapp_tm_order_code" e_value=a_orderCode />
<@m_zapmacro_mobile_html_hidden e_id="yesapp_tm_member_code" e_value=a_memberCode />

<#assign a_member_info=b_method.upDataOne("yh_member_extend_geracomium","","","","member_code",a_memberCode) />




${a_member_info["member_name"]}
<div class="zmcss_h_20"></div>
<textarea rows="5"  placeholder="查房记录"></textarea>


 </div>


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
 
 <@m_mobile_html_end />






