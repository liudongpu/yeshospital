<#-- 系统版本号 -->
<#assign a_macro_mobile_system_version=b_method.upConfig("zapcom.version")>
<#-- 资源附加后缀版本 -->
<#assign a_macro_mobile_resources_version="?v="+a_macro_mobile_system_version >





<#-- 资源文件路径 -->
<#assign a_macro_mobile_resources_link="../" >

<#-- 图片文件路径 -->
<#assign a_macro_mobile_img_link="../" >



<#if (b_method.upConfig("zapcom.model")=="default")>
	<#assign a_macro_mobile_resources_version="?v="+a_macro_mobile_system_version+.now >
	<#assign a_macro_mobile_resources_link="../" >
	
</#if>



<#-- 项目特殊样式 -->

<#assign a_macro_mobile_resources_base_js=["mlib/apicloud/script/api.js","mlib/jquery/jquery-2.1.4.min.js"] >
<#assign a_macro_mobile_resources_thems_js=["mlib/jquery/jquery-plugins-zap-mb.min.js","zapmobile/js/zmapi.js","zapmobile/js/zmjs.js","zapmobile/js/zapjs-mb.js","zapmobile/js/zapjs-mb.zw-mb.js","yesapp/js/yesapp.js"] >
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


<#macro m_mobile_html_begin  p_title="" p_type="" p_css=[] p_js=[]>
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
	<@m_mobile_html_js p_js />
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
	
	<div id="zmcss_mm_page_header" data-role="header" style="overflow:hidden;"  data-position="fixed" >
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



<#macro m_mobile_event_input p_js="">
 oninput="${p_js}" onkeyup="${p_js}" onkeypress="${p_js}"
</#macro>



<#-- 返回按钮  -->
<#macro m_mobile_button_back >

	<a <@m_mobile_a_back/>  data-icon="arrow-l" data-iconpos="notext" data-ajax="false">返回</a>
	
</#macro>




<#-- 初始化数据操作脚本  -->
<#macro m_mobile_init_dbcall>
<#assign a_macro_mobile_dbcall=b_method.upClass("com.srnpr.zapweb.websupport.DataCallSupport") >
</#macro>





<#-- 初始化扩展包  -->
<#macro m_mobile_init_extendlib>
	<@m_mobile_html_css ["mlib/mobiscroll/css/mobiscroll.custom-2.16.1.min.css"] />
	<@m_mobile_html_js ["mlib/mobiscroll/js/mobiscroll.custom-2.16.1.min.js"] />
</#macro>




<#-- 图像元素  -->
<#macro m_mobile_html_img p_link="" p_alt="" p_width=-1 p_img="" >

<#if p_img!="" >
<#if p_link!=""><a href="${p_link}"></#if>

<#if ((p_width>-1)&&((p_img?ends_with(".jpg"))||(p_img?ends_with(".jpeg"))||(p_img?ends_with(".png"))))><#local p_img=p_img?replace(".jpg","-"+(p_width)+"-1000.jpg")?replace(".jpeg","-"+(p_width)+"-1000.jpeg")?replace(".png","-"+(p_width)+"-1000.png")?replace(".com/",".com/qsize/")?replace(".cn/",".cn/qsize/") /></#if>
<img src="${p_img}" alt="${p_alt}" /><#if p_link!=""></a></#if>

</#if>
</#macro>






