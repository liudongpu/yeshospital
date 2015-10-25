<#include "../macro/macro_mobile.ftl" />
<#include "../zapmacro/zapmacro_uset.ftl" />


<#assign a_user_code=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport").upMouldInitCode() />
 <@m_mobile_init_dbcall />


<@m_mobile_html_begin />
<@m_mobile_body_begin />

<@m_mobile_header_begin p_title="模板管理" />
	<@m_mobile_button_back />
	 
	
<@m_mobile_header_end />


<div class=" zmcss_w_96">
	<div class="zmcss_h_20"></div>
	
		<div data-role="tabs" id="tabs">
  <div data-role="navbar">
    <ul>
      <li><a href="#one" data-ajax="false">查房记录模板</a></li>
      <li><a href="#two" data-ajax="false">查房建议模板</a></li>
      <li><a href="#three" data-ajax="false">检验信息模板</a></li>
    </ul>
  </div>
  <div class="zmcss_h_20"></div>
  
  
  <div id="one" class="ui-body-d ui-content">
    <#assign a_list=a_macro_mobile_dbcall.queryAll("yh_mould_info","","","","mould_type","46580001000200100001","user_code",a_user_code) >
	<ul data-role="listview">
	<#list a_list as el>
    
    	<li >${el["mould_content"]}</li>
    	
    </#list>
	</ul>
  </div>
  <div id="two">
  	 <#assign a_list=a_macro_mobile_dbcall.queryAll("yh_mould_info","","","","mould_type","46580001000200100002","user_code",a_user_code) >
    <ul data-role="listview">
	<#list a_list as el>
    
    	<li >${el["mould_content"]}</li>
    	
    </#list>
	</ul>
  </div>
	<div id="three">
   
	 <#assign a_list=a_macro_mobile_dbcall.queryAll("yh_mould_info","","","","mould_type","46580001000200100003","user_code",a_user_code) >
    <ul data-role="listview">
	<#list a_list as el>
    
    	<li >${el["mould_content"]}</li>
    	
    </#list>
	</ul>

  	</div>
	
	<div class="zmcss_h_20"></div>
		
	
	
	
	
	

 </div>
	</div>
</div>
	
	
	
	
	
 </div>

 <@m_mobile_body_end  />
 

 <@m_mobile_html_end />

