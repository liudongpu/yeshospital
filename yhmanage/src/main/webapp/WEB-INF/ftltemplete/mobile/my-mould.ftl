<#include "../macro/macro_mobile.ftl" />
<#include "../zapmacro/zapmacro_uset.ftl" />





<#assign a_app_info=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport") />

 <@m_mobile_init_dbcall />


<@m_mobile_html_begin />
<@m_mobile_body_begin />

<@m_mobile_header_begin p_title="模板管理" />
	<@m_mobile_button_back />
	 <a <@m_mobile_a_href p_page="../mb/page_add_d_yh_mould_info" /> class="ui-btn ui-btn-inline">添加</a>
	
<@m_mobile_header_end />


<div class=" ">
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
    <#assign a_list=a_app_info.upMouldList("mould_type=:mould_type and parent_code=0 ","mould_type","46580001000200100001") >
	<ul data-role="listview">
	<#list a_list as el>
    
    	<li ><a <@m_mobile_a_href p_page="mould-list?u_uid="+el["uid"] /> >${el["mould_content"]}</a></li>
    	
    </#list>
	</ul>
  </div>
  <div id="two">
  	 <#assign a_list=a_app_info.upMouldList("mould_type=:mould_type  and parent_code=0 ","mould_type","46580001000200100002") >
    <ul data-role="listview">
	<#list a_list as el>
    
    	<li ><a <@m_mobile_a_href p_page="mould-list?u_uid="+el["uid"] /> >${el["mould_content"]}</a></li>
    	
    </#list>
	</ul>
  </div>
	<div id="three">
   
	 <#assign a_list=a_app_info.upMouldList("mould_type=:mould_type  and parent_code=0 ","mould_type","46580001000200100003") >
    <ul data-role="listview">
	<#list a_list as el>
    
    	<li ><a <@m_mobile_a_href p_page="mould-list?u_uid="+el["uid"] /> >${el["mould_content"]}</a></li>
    	
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

