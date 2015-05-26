 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<#assign a_memberCode=RequestParameters['u_member_code']?default("") >


<#assign b_page=b_method.upControlPage("page_book_d_v_yh_member_extend_geracomium","zw_f_member_code="+a_memberCode) />


<@m_mobile_header_begin p_title="个人信息" />
	<@m_mobile_button_back />
<@m_mobile_header_end />

	<div class="zmcss_h_20"></div>
	
	
	<div data-role="tabs" id="tabs">
  <div data-role="navbar">
    <ul>
      <li><a href="#one" data-ajax="false">基本信息</a></li>
      <li><a href="#two" data-ajax="false">查房历史</a></li>
      <li><a href="#three" data-ajax="false">健康数据</a></li>
    </ul>
  </div>
  <div id="one" class="ui-body-d ui-content">
    <@m_zapmacro_mobile_page_book b_page />	
  </div>
  <div id="two">
    <ul data-role="listview">
    <@m_mobile_init_dbcall />

	<#assign a_list=a_macro_mobile_dbcall.queryAll("yh_tour_order_detail","","","","member_code",a_memberCode) >
    <#list a_list as el>
    
    	<li data-role="list-divider">${el["create_time"]}</li>
    	<li>${el["tour_info"]}</li>
    </#list>
    
    
</ul>
  </div>

	
	

	
	
	
	
	

 </div>
 
 
 
 
 


 <@m_mobile_body_end />
 

 <@m_mobile_html_end />






