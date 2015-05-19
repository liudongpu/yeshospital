

<div class="zab_info_page">

<div class="zab_info_page_title  w_clear">
<span>基本信息</span>
</div>


<@m_zapmacro_common_page_book b_page />

<#assign  a_order_code=b_method.upFiledByFieldName(b_page.upBookData(),"tour_code").getPageFieldValue() />


<div class="zab_info_page_title  w_clear">
<span>明细信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_show_chart_v_yh_tour_order_detail","zw_f_tour_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>购药信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_show_chart_v_yh_tour_order_drug","zw_f_tour_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />

<div class="zab_info_page_title  w_clear">
<span>日志信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_tour_order_log","zw_f_order_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />



