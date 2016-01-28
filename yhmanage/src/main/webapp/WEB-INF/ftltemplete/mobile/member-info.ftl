 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />

 <@m_mobile_init_dbcall />
<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
<#assign a_member_info=b_method.upDataOne("yh_member_extend_geracomium","","","","member_code",a_memberCode) />

<#assign b_page=b_method.upControlPage("page_book_d_v_yh_member_extend_geracomium","zw_f_member_code="+a_memberCode) />


<@m_mobile_header_begin p_title="个人信息" />
	<@m_mobile_button_back />
	<a <@m_mobile_a_href p_page="../mb/page_edit_d_v_yh_member_extend_geracomium?zw_f_uid="+a_member_info["uid"] /> class="ui-btn ui-btn-inline">修改</a>
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
    <#assign a_druglist=a_macro_mobile_dbcall.queryAll("yh_member_drug","drug_name","-zid","","member_code",a_memberCode) >

<div class="zmcss_h_20"></div>
    服药信息：
    <div class="zmcss_h_10"></div>
    <ul >
    <#list a_druglist as el>
		<li >${el["drug_name"]}</li>
	</#list>
	</ul>
	<div class="zmcss_h_20"></div>
	
  </div>
  <div id="two">
    <ul data-role="listview">
   

	<#assign a_list=a_macro_mobile_dbcall.queryAll("yh_tour_order_detail","create_time,tour_info,agree_info,check_info,(select doctor_name from yh_doctor_info where user_code=yh_tour_order_detail.create_user) as doctor_name","-zid","","member_code",a_memberCode) >
    <#list a_list as el>
    
    	<li data-role="list-divider">${el["create_time"]}</li>
    	<li><div style="white-space: normal;"><b>查房记录：</b>${el["tour_info"]}<br/><b>查房建议：</b>${el["agree_info"]}<br/><b>健康建议：</b>${el["check_info"]}<br/><b>操作人：</b>${el["doctor_name"]}</div></li>
    </#list>
    
    
	</ul>
  </div>
	<div id="three">
   
	<#assign a_report_info=b_method.upControlPage("page_book_v_yh_report_info","zw_f_member_code="+a_memberCode)>
	<@m_zapmacro_mobile_page_book a_report_info />

  	</div>
	
	

	
	
	
	
	

 </div>
 
 
 
 
 


 <@m_mobile_body_end />
 

 <@m_mobile_html_end />






