

<div class="zab_info_page">

<div class="zab_info_page_title  w_clear">
<span>基本信息</span>
</div>


<@m_zapmacro_common_page_book b_page />

<#assign  a_order_code=b_method.upFiledByFieldName(b_page.upBookData(),"visit_order_code").getPageFieldValue() />
<#assign  a_member_code=b_method.upFiledByFieldName(b_page.upBookData(),"member_code").getPageFieldValue() />
<#assign  a_sib_code=b_method.upFiledByFieldName(b_page.upBookData(),"sib_code").getPageFieldValue() />
<div class="zab_info_page_title  w_clear">
<span>明细信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_visit_order_detail","zw_f_visit_order_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>老人信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_book_d_yh_member_extend_geracomium","zw_f_member_code="+a_member_code)>
<@m_zapmacro_common_page_book a_order_detail />

<div class="zab_info_page_title  w_clear">
<span>家属信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_book_m_yh_sib_info","zw_f_sib_code="+a_sib_code)>
<@m_zapmacro_common_page_book a_order_detail />


<div class="zab_info_page_title  w_clear">
<span>日志信息</span>
</div>
<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_visit_process_log","zw_f_order_code="+a_order_code).upChartData()>
<@m_zapmacro_common_table a_order_detail />

