

<div class="zab_info_page">

<div class="zab_info_page_title  w_clear">
<span>基本信息</span>
</div>


<@m_zapmacro_common_page_book b_page />

<#assign  a_order_code=b_method.upFiledByFieldName(b_page.upBookData(),"tour_code").getPageFieldValue() />

<#assign  a_order_sum=b_method.upDataOne("yh_tour_invoice","sum(case money_type when '46580001000400040001' then money_all else 0 end) as a_ma,sum(case money_type when '46580001000400040001' then money_person else 0 end) as a_mp,sum(case money_type when '46580001000400040001' then 0 else money_all end) as b_ma,sum(case money_type when '46580001000400040001' then 0 else money_person end) as b_mp",
						"", "", "tour_code", a_order_code) />


<form  class="form-horizontal">
<div class="control-group">
	<label class="control-label" for="">购药总金额:</label>
	<div class="controls">

	      		<div class="control_book">
		      		${a_order_sum["a_ma"]}
	      		</div>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="">购药自费金额:</label>
	<div class="controls">

	      		<div class="control_book">
		      		${a_order_sum["a_mp"]}
	      		</div>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="">化验总金额:</label>
	<div class="controls">

	      		<div class="control_book">
		      		${a_order_sum["b_ma"]}
	      		</div>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="">化验自费金额:</label>
	<div class="controls">

	      		<div class="control_book">
		      		${a_order_sum["b_mp"]}
	      		</div>
	</div>
</div>

</form>


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



