<#include "../zapmacro/zapmacro_mobile.ftl" />
<#include "../macro/macro_mobile.ftl" />




<#assign a_app_info=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport") />
 <@m_mobile_init_dbcall />


<@m_mobile_html_begin />
<@m_mobile_body_begin />

<@m_mobile_header_begin p_title="模板维护" />
	<@m_mobile_button_back />
	 
	
<@m_mobile_header_end />
<#assign a_uid=RequestParameters['u_uid']?default("") >
<#assign a_mould_info=b_method.upDataOne("yh_mould_info","","","","uid",a_uid) />
<div class="zmcss_o_a zmcss_w_96  ">
	<div class="zmcss_h_20"></div>
	<h4>${a_mould_info["mould_content"]}</h4>
	<div class="zmcss_h_20"></div>
	
	
	
		
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_vo_mould_code" e_value=a_mould_info["model_code"] />	
	<#assign a_visit_money=a_app_info.upMouldList("parent_code=:parent_code ","parent_code",a_mould_info["model_code"]) >
	<a href="#popup_nested" data-rel="popup" data-position-to="window" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-plus ui-btn-icon-left " >添加自定义模板</a>
	
	
	<ol   data-role="listview" data-inset="true"  id="yesapp_vo_table" >
		    
	</ol>
	
	
	<div data-role="popup" id="popup_nested" data-dismissible="false"  data-overlay-theme="a" data-theme="a" style="margin:0;min-width:300px; ">
		<div data-role="header" data-theme="a" data-icon="delete">
	    <h1>请输入内容</h1>
	    <a href="#" data-rel="back" class="ui-btn ui-shadow ui-corner-all ui-icon-delete ui-btn-icon-notext ui-btn-inline ">Delete</a>
		    
	    </div>
	    <div role="main" class="ui-content">
	        <h3 class="ui-title"></h3>
	    <p><textarea  name="yesapp_vo_add" id="yesapp_vo_add" ></textarea> </p>
	        <a href="javascript:yesapp_mould.mould_add()" class="ui-btn ui-corner-all ui-shadow ui-btn-inline " >确认添加</a>
	        
	    </div>
	</div>
	
	
	<div data-role="popup" id="popup_money" data-dismissible="false"  data-overlay-theme="a" data-theme="a" style="margin:0;min-width:300px; ">
		<div data-role="header" data-theme="a" data-icon="delete">
	    <h1>请输入内容</h1>
	    <a href="#" data-rel="back" class="ui-btn ui-shadow ui-corner-all ui-icon-delete ui-btn-icon-notext ui-btn-inline ">Delete</a>
		    
	    </div>
	    <div role="main" class="ui-content">
	        <h3 class="ui-title"></h3>
	    <p><textarea  name="yesapp_vo_number" id="yesapp_vo_number" ></textarea> </p>
	        <a href="javascript:yesapp_mould.mould_edit()" class="ui-btn ui-corner-all ui-shadow ui-btn-inline " >确认修改</a>
	        <a href="javascript:yesapp_mould.mould_del()" class="ui-btn ui-corner-all ui-shadow ui-btn-inline " >删除</a>
	    </div>
	</div>
	
	
	
	</div>
	
	
	
	
	
 </div>

 <@m_mobile_body_end   p_js=["yesapp/js/yesapp-mould.js"]/>
 
 <@m_mobile_html_initjs e_js="yesapp_mould.init_mould_order()" />
 <@m_mobile_html_end />

