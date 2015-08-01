

<#include "../macro/macro_report.ftl" />


<@m_report_show_js />


<#assign a_order_code=RequestParameters['zw_f_member_code']?default("xxxxx") >



<div class="zab_info_page">





	<div class="zab_info_page_title  w_clear">
	<span>用户基本信息</span>
	</div>

	<@m_zapmacro_common_page_book b_page />


	<#if a_order_code!="" >
	<div class="zab_info_page_title  w_clear">
	<span>报告信息</span>
	</div>
	<#assign a_order_detail=b_method.upControlPage("page_book_v_yh_report_info","zw_f_member_code="+a_order_code)>
	<@m_zapmacro_common_page_book a_order_detail />
	</#if>	


	<div class="zab_info_page_title  w_clear">
	<span>查房信息</span>
	</div>
	<#assign a_order_detail=b_method.upControlPage("page_chart_v2_yh_tour_order_detail","zw_f_member_code="+a_order_code).upChartData()>
	<@m_zapmacro_common_table a_order_detail />

	<div class="zab_info_page_title  w_clear">
	<span>用药信息</span>
	</div>
	<#assign a_order_detail=b_method.upControlPage("page_chart_report_yh_tour_order_drug","zw_f_member_code="+a_order_code).upChartData()>
	<@m_zapmacro_common_table a_order_detail />

	<#if a_order_code!="" >

	<div class="zab_info_page_title  w_clear">
	<span>血压信息</span>
	<a href="page_chart_r_yh_post_pressure?zw_f_member_code=${a_order_code}" target="_blank">查看全部</a>
	</div>
	
	<@m_report_highchart_date_show e_page=b_method.upControlPage("page_chart_r_yh_post_pressure","zw_f_member_code="+a_order_code) e_id="yh_post_pressure"/>
	
	<div class="zab_info_page_title  w_clear">
	<span>体温信息</span>
	<a href="page_chart_r_yh_post_temperature?zw_f_member_code=${a_order_code}" target="_blank">查看全部</a>
	</div>
	
	<@m_report_highchart_date_show e_page=b_method.upControlPage("page_chart_r_yh_post_temperature","zw_f_member_code="+a_order_code) e_id="yh_post_temperature"/>
	
	
	<div class="zab_info_page_title  w_clear">
	<span>血氧信息</span>
	<a href="page_chart_r_yh_post_oxygen?zw_f_member_code=${a_order_code}" target="_blank">查看全部</a>
	</div>
	
	<@m_report_highchart_date_show e_page=b_method.upControlPage("page_chart_r_yh_post_oxygen","zw_f_member_code="+a_order_code) e_id="yh_post_oxygen"/>
	
	
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
	<a href="page_chart_r_yh_post_glucose?zw_f_member_code=${a_order_code}" target="_blank">查看全部</a>
	</div>
	
	<@m_report_highchart_date_show e_page=b_method.upControlPage("page_chart_r_yh_post_glucose","zw_f_member_code="+a_order_code) e_id="yh_post_glucose"/>
	
	

	<div class="zab_info_page_title  w_clear">
	<span>健康建议</span>
	</div>
	<#assign a_order_detail=b_method.upControlPage("page_chart_v_yh_count_advice","zw_f_member_code="+a_order_code).upChartData()>
	<@m_zapmacro_common_table a_order_detail />

	</#if>	

