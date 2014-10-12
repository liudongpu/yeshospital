
<#macro m_report_html_js>
	<@m_common_html_js ["../resources/lib/highcharts/js/highcharts.js","../resources/lib/highcharts/js/modules/exporting.js","../resources/lib/highcharts/js/themes/grid.js"]/>
	<style>
	  .highcharts-contextmenu{
 	width:50px !important;
 }
 .highcharts-contextmenu div{
 	height:20px !important;
 	line-height:20px;
 	scroll:no  !important;
 	overflow-y:hidden  !important;
 }
 
 .highcharts-container text
 {
 	
 }
 
	</style>
	<div style="width:90%;margin-left:30px;margin-bottom:30px;">
			<div id="container" style="min-width: 610px; min-height: 400px; margin: 0 auto"></div>
	</div>
	
	
</#macro>

<#-- 报表 -->
<#macro m_report_highchart_column e_page>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date e_page,e_page.upChartData(),"column" />
</#macro>


<#macro m_report_highchart_area e_page>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date e_page,e_page.upChartData(),"area" />
</#macro>


<#macro m_report_highchart_line e_page>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date e_page,e_page.upChartData(),"line" />
</#macro>




<#-- 报表的自动输出 -->
<#macro m_report_highchart_date e_page,e_pagedata,e_chart_type="line">
	
	<#assign dataScope=e_page.getWebPage().getDataScope() />
	<#assign mapHelper=b_method.upClass("com.srnpr.zapcom.basehelper.MapHelper")>
	
	
	<#assign scopeMap = mapHelper.getDataMapFromDataScop(dataScope?default(""))>
	
	<#assign title="报表信息" />
	<#assign XAxis=""  />
	<#assign YAxisTitle=""  />
	
	<script>
		var seriesData1 = [<#list e_pagedata.getPageHead() as e_list><#if e_list_index=0><#else>{ name:"${e_list?default("")}",data:[<#list e_pagedata.getPageData() as e_list_data><#list e_list_data as e><#if e_index = e_list_index><#if (e?default(""))="">0<#else>${e}</#if><#if e_list_data_has_next>,</#if><#else></#if></#list></#list>]}<#if e_list_has_next>,</#if></#if></#list>];		
		var categroesData1 = [<#list e_pagedata.getPageData() as e_list><#list e_list as e><#if e_index = 0>"${e?default("")}"<#else></#if></#list><#if e_list_has_next>,</#if></#list>];
		var chartTitle1="${title?default("")}";
		var chartSubtitle1="";
		var chartYtitle1 = "${YAxisTitle?default("")}";
		
		
		
		
		zapwebreport2={
		showHighCharts : function (chartTitle,chartSubtitle,categroesData,seriesData,chartYtitle) {
	        $('#container').highcharts({
	            chart: {
	                type: '${e_chart_type}',
	                marginRight: 30 
	            },
	            credits : { enabled:false//不显示highCharts版权信息 
	            },
	            title: {
	                text: chartTitle
	            },
	            subtitle: {
	                text: chartSubtitle
	            },
	            xAxis: {
	                categories: categroesData
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: chartYtitle
	                }
	            },
	            tooltip: {
	                headerFormat: '<span style="font-size:12px">{point.key}</span><table style="width:150px;">',
	                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                    '<td style="padding:0"><b>{point.y} </b></td></tr>',
	                footerFormat: '</table>',
	                shared: true,
	                useHTML: true
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.1,
	                    groupPadding: 0.1,
	                    borderWidth: 0
	                }
	            },
	            series: seriesData
	        });
	   }
};




$(document).ready(function(){
	 	zapwebreport2.showHighCharts(chartTitle1,chartSubtitle1,categroesData1,seriesData1,chartYtitle1);
	});


	</script>
	
	
	
	
</#macro>




