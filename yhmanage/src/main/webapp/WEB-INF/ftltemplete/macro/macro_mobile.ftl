<#-- 系统版本号 -->
<#assign a_macro_mobile_system_version="2.0.0.16">
<#-- 资源附加后缀版本 -->
<#assign a_macro_mobile_resources_version="?v="+a_macro_mobile_system_version >
<#-- 资源文件路径 -->
<#assign a_macro_mobile_resources_link="../" >
<#-- 项目特殊样式 -->

<#assign a_macro_mobile_resources_base_js=["mlib/jquery/jquery-2.1.4.min.js"] >
<#assign a_macro_mobile_resources_thems_js=["mlib/apicloud/script/api.js","lib/jquery/jquery-plugins-zap.min.js","zapmobile/js/zmapi.js","zapmobile/js/zmjs.js","zapmobile/js/zapjs-mb.js","zapmobile/js/zapjs-mb.zw-mb.js","yesapp/js/yesapp.js","yesapp/js/yesapp-frame.js"] >
<#assign a_macro_mobile_resources_thems_css=["mlib/apicloud/css/api.css","zapmobile/css/zmcss.css","yesapp/css/yes-base.css"] >


<#assign a_macro_mobile_resources_append_css=["mlib/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css"] >
<#assign a_macro_mobile_resources_append_js=["mlib/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"] >

<#macro m_mobile_html_js e_list>
	<#list e_list as e>
	<script type="text/javascript" src="${a_macro_mobile_resources_link}resources/${e}${a_macro_mobile_resources_version}"></script>
	</#list>
</#macro>
<#macro m_mobile_html_css e_list >
    <#list e_list as e>
	<link type="text/css" href="${a_macro_mobile_resources_link}resources/${e}${a_macro_mobile_resources_version}" rel="stylesheet">
	</#list>
</#macro>

<#macro m_mobile_html_script  e_info >

	<script type="text/javascript">
		${e_info}
	</script>

</#macro>

<#-- 页面初始化时执行脚本 -->
<#macro m_mobile_html_initjs  e_js >
<@m_mobile_html_script "$(function(){"+e_js+"}); " />

</#macro>


<#macro m_mobile_html_begin  p_title="" p_type="" p_css=[]>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <title>${p_title}</title>
    
  <@m_mobile_html_js a_macro_mobile_resources_base_js />
   <#if p_type!="index">
		<@m_mobile_html_css a_macro_mobile_resources_append_css />
		<@m_mobile_html_js a_macro_mobile_resources_append_js />	
	</#if>

	<@m_mobile_html_css    a_macro_mobile_resources_thems_css />
	 <@m_mobile_html_css    p_css />
	 
	 
	 
	 <#-- 开发时使用 
	 <link rel="stylesheet/less" type="text/css" href="${a_macro_mobile_resources_link}resources/zapmobile/less/zmcss.less" />
	<@m_mobile_html_js ["mlib/less/less.min.js"] />

	-->
	
</#macro>



<#macro m_mobile_body_begin>

</head>
<body>

</#macro>

<#macro m_mobile_body_end  p_type="" p_js=[]>


<@m_mobile_html_js a_macro_mobile_resources_thems_js />



<@m_mobile_html_js p_js />


</#macro>

<#macro m_mobile_html_end  >

</body>
</html>
</#macro>


<#macro m_mobile_header_begin p_title="" >
	
	<div id="zmcss_mm_page_header" data-role="header" style="overflow:hidden;"  data-position="fixed">
	<h1>${p_title}</h1>
</#macro>


<#macro m_mobile_header_end  >
	
	</div>

</#macro>




<#macro m_mobile_call_page  p_page="">
 onclick="zmjs.page.open_page('${p_page}')" 
</#macro>

<#macro m_mobile_back_page>
 onclick="zmjs.page.back_page()" 
</#macro>

<#macro m_mobile_a_back>
 href="javascript:zmjs.page.back_page()" 
</#macro>

<#macro m_mobile_a_href  p_page="">
  href="javascript:zmjs.page.open_page('${p_page}')" 
</#macro>



<#macro m_mobile_back_root>
 onclick="zmjs.page.back_root()" 
</#macro>


<#-- 返回按钮  -->
<#macro m_mobile_button_back >

	<a <@m_mobile_a_back/>  data-icon="arrow-l" data-iconpos="notext" data-ajax="false">返回</a>
	
</#macro>




<#-- 初始化数据操作脚本  -->
<#macro m_mobile_init_dbcall>
<#assign a_macro_mobile_dbcall=b_method.upClass("com.srnpr.zapweb.websupport.DataCallSupport") >
</#macro>








