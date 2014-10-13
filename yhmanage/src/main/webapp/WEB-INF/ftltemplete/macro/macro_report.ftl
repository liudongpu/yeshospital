
<#macro m_report_html_js e_themes="grid">
	<@m_common_html_js ["../resources/lib/highcharts/js/highcharts.js","../resources/lib/highcharts/js/modules/exporting.js"]/>
	<#if e_themes!="">
		<@m_common_html_js ["../resources/lib/highcharts/js/themes/${e_themes}.js"]/>
	</#if>
	
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
			<div id="container" style="min-width: 610px; min-height: 80%; margin: 0 auto"></div>
	</div>
	
	
</#macro>

<#-- 去掉版权信息 -->
<#macro m_report_js_credits >
credits : { enabled:false},
</#macro>

<#-- 报表 -->
<#macro m_report_highchart_column e_page>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date_default e_page,e_page.upChartData(),"column" />
</#macro>


<#macro m_report_highchart_area e_page>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date_default e_page,e_page.upChartData(),"area" />
</#macro>


<#macro m_report_highchart_line e_page>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date_default e_page,e_page.upChartData(),"line" />
</#macro>




<#-- 常规报表的自动输出 -->
<#macro m_report_highchart_date_default e_page,e_pagedata,e_chart_type="line">
	
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
	            <@m_report_js_credits />
	            
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



<#-- 饼状图的展示 -->
<#macro m_report_highchart_pie e_page>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date_pie e_page,e_page.upChartData() />
</#macro>

<#-- 饼状图的数据输出 -->
<#macro m_report_highchart_date_pie e_page,e_pagedata>




<script>

var report_title='报表信息';
var report_series_date = [<#list e_pagedata.getPageData() as e_list>['${e_list[0]}',${e_list[1]}]<#if e_list_has_next>,</#if></#list>];


$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        <@m_report_js_credits />
        title: {
            text: report_title
        },
        tooltip: {
    	    pointFormat: ' <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '',
            data: report_series_date
        }]
    });
});

</script>

</#macro>




<#-- 极地蛛网图的展示 -->
<#macro m_report_highchart_spider e_page>

	<@m_common_html_js ["../resources/lib/highcharts/js/highcharts-more.js"]/>
	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_report_highchart_date_spider e_page,e_page.upChartData() />
</#macro>

<#-- 极地蛛网图的数据输出 -->
<#macro m_report_highchart_date_spider e_page,e_pagedata>




<script>

var report_title='报表信息';
var report_series_date = [{name:'${e_pagedata.getPageHead()[1]}',data:[<#list e_pagedata.getPageData() as e_list>${e_list[1]}<#if e_list_has_next>,</#if></#list>],pointPlacement: 'on'}];

var report_categories_date = [<#list e_pagedata.getPageData() as e_list>'${e_list[0]}'<#if e_list_has_next>,</#if></#list>];
$(function () {

    $('#container').highcharts({

        chart: {
            polar: true,
            type: 'line'
        },
		<@m_report_js_credits />
        title: {
            text: report_title
        },

        pane: {
            size: '80%'
        },

        xAxis: {
            categories: report_categories_date,
            tickmarkPlacement: 'on',
            lineWidth: 0
        },

        yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0
        },

        tooltip: {
            shared: true,
            pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>'
        },

        legend: {
            align: 'right',
            verticalAlign: 'top',
            y: 70,
            layout: 'vertical'
        },

        series: report_series_date

    });
});


</script>

</#macro>

