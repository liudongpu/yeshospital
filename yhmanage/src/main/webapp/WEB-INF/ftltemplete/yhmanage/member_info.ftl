

<div class="zab_info_page">

<div class="zab_info_page_title  w_clear">
<span>用户信息</span>
</div>


<@m_zapmacro_common_page_book b_page />

<#assign  a_order_code=b_method.upFiledByFieldName(b_page.upBookData(),"member_code").getPageFieldValue() />



<div class="zab_info_page_title  w_clear">
<span>关联设备信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_member_device","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>关联医院</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_member_hospital","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />





