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
    
	
  </div>
  <div id="two">
    <ul data-role="listview">
   

	<#assign a_list=a_macro_mobile_dbcall.queryAll("yh_tour_order_detail","create_time,tour_info,agree_info,check_info,(select doctor_name from yh_doctor_info where user_code=yh_tour_order_detail.create_user) as doctor_name","-zid","","member_code",a_memberCode) >
    <#list a_list as el>
    
    	<li data-role="list-divider">${el["create_time"]}</li>
    	<li><div style="white-space: normal;"><b>查房记录：</b>${el["tour_info"]}(${el["doctor_name"]})<br/><b>查房建议：</b>${el["agree_info"]}(${el["doctor_name"]})<br/></div></li>
    </#list>
    
    
	</ul>
  </div>
	<div id="three">
   <#--
	<#assign a_report_info=b_method.upControlPage("page_book_v_yh_report_info","zw_f_member_code="+a_memberCode)>
	<@m_zapmacro_mobile_page_book a_report_info />
	-->
		<div class="zmcss_h_20"></div>
		
		<div  data-role="collapsibleset">
			<div data-role="collapsible" data-collapsed="false">
				<#assign a_health_list=a_macro_mobile_dbcall.queryAll("yh_member_drug","drug_name","-zid","","member_code",a_memberCode) />
			    <h4>服药信息&nbsp;-${a_health_list?size}条</h4>
			    
			    <ul  data-role="listview"> 
			    <#list a_health_list as el>
					<li  class="yb_space">${el["drug_name"]} </li>
				</#list>
				</ul>
			    
			</div>
		
		
			<div data-role="collapsible">
				<#assign a_health_list=a_macro_mobile_dbcall.query("yh_post_temperature","","-zid","",0,10,"member_code",a_memberCode) />
			    <h4>体温数据&nbsp;-${a_health_list?size}条</h4>
			    
			    <ul  data-role="listview"> 
			    <#list a_health_list as el>
					<li  class="yb_space">${el["post_time"]}&nbsp;&nbsp;&nbsp;&nbsp;体温：${el["temperature"]} </li>
				</#list>
				</ul>
			    
			</div>
			
			<div data-role="collapsible">
				<#assign a_health_list=a_macro_mobile_dbcall.query("yh_post_pressure","","-zid","",0,10,"member_code",a_memberCode) />
			    <h4>血压数据&nbsp;-${a_health_list?size}条</h4>
			    <ul  data-role="listview"> 
			    <#list a_health_list as el>
					<li  class="yb_space">${el["post_time"]}<br/>心率：${el["heart_rate"]}&nbsp;&nbsp;&nbsp;&nbsp;收缩压：${el["upper_pressure"]}&nbsp;&nbsp;&nbsp;&nbsp;舒张压：${el["lower_pressure"]} </li>
				</#list>
				</ul>
			</div>

			<div data-role="collapsible">
				<#assign a_health_list=a_macro_mobile_dbcall.query("yh_post_oxygen","","-zid","",0,10,"member_code",a_memberCode) />
			    <h4>血氧数据&nbsp;-${a_health_list?size}条</h4>
			    <ul  data-role="listview"> 
			    <#list a_health_list as el>
					<li  class="yb_space">${el["post_time"]}&nbsp;&nbsp;&nbsp;&nbsp;心率：${el["heart_rate"]}&nbsp;&nbsp;&nbsp;&nbsp;血氧：${el["oxygen"]} </li>
				</#list>
				</ul>
			    
			</div>
			<div data-role="collapsible">
				<#assign a_health_list=a_macro_mobile_dbcall.query("yh_post_glucose","","-zid","",0,10,"member_code",a_memberCode) />
			    <h4>血糖数据&nbsp;-${a_health_list?size}条</h4>
			    <ul  data-role="listview"> 
			    <#list a_health_list as el>
					<li  class="yb_space">${el["post_time"]}&nbsp;&nbsp;&nbsp;&nbsp;心率：${el["glucose"]} </li>
				</#list>
				</ul>
			    
			</div>
		</div>
  	</div>
	
	

	
	
	
	
	

 </div>
 
 
 
 
 


 <@m_mobile_body_end />
 

 <@m_mobile_html_end />






