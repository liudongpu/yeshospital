

<div class="zab_info_page">

<div class="zab_info_page_title  w_clear">
<span>用户报告信息</span>
</div>


<@m_zapmacro_common_page_book b_page />

<#assign  a_order_code=b_method.upFiledByFieldName(b_page.upBookData(),"member_code").getPageFieldValue() />



<div class="zab_info_page_title  w_clear">
<span>血压信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_post_pressure","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>体温信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_post_temperature","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>血氧信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_post_oxygen","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>定位信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_post_location","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>心电图信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_post_electrocardiogram","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>血糖信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_post_glucose","zw_f_member_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />




