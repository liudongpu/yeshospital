<#-- 设置页 -->



<#macro m_zapmacro_set_page_show e_result>


<div class="zmcss_set_box">

<#list e_result.getInfos() as e_item>
	
	<div class="zmcss_set_item">
		<div  class="zmcss_set_left">${e_item.getFieldNote()}</div>

		<div  class="zmcss_set_right">${e_item.getFieldValue()}</div>

	</div>

	
</#list>
<div class="zmcss_w_clear"></div>
</div>

</#macro>