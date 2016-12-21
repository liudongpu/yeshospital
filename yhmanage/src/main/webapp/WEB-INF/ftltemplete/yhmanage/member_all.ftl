

<#include "../macro/macro_report.ftl" />


<@m_report_show_js />


<#assign a_order_code=RequestParameters['zw_f_member_code']?default("xxxxx") >
<#assign a_macro_dbcall=b_method.upClass("com.srnpr.zapweb.websupport.DataCallSupport") >



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
	<#assign a_order_detail=b_method.upControlPage("page_chart_r_yh_member_drug","zw_p_size=-1&zw_f_member_code="+a_order_code).upChartData()>
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
<#assign a_pic_detail=a_macro_dbcall.queryAll("yh_member_pic","","-zid","","member_code",a_order_code)>
<div class="zab_info_page_title  w_clear">
	<span>用户图片</span>
	</div>
	<div>
		<#list a_pic_detail as a_det>
		
			<div style="width:100px;float:left;border:solid 1px #ccc;padding:5px;margin-left:9px;">
				<a href="${a_det['pic_url']}" target="_blank"><img src="${a_det['pic_url']}"/></a>
			</div>
			
		</#list>
		<div style="clear:both;"></div>
	</div>
