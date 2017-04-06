<#include "../macro/macro_report.ftl" />

<input type="button" class="btn" onclick="zapadmin.window_url('../show/page_zapadmin_window_change_password')" value="修改密码"/>


<#assign a_user_info= b_method.upClass("com.srnpr.zapweb.websupport.UserSupport").getUserInfo() />
<#assign a_voip_info= b_method.upClass("com.srnpr.yeshospital.support.RongCloudSupport") />

<#if ((a_user_info.manageCode?trim=="")||(a_user_info.loginName?starts_with("110")))>
<a  class="btn" href="${b_method.upConfig('yeshospital.rtc_server')}voip?u_model=0&u_user=${a_user_info.loginName}&u_client=voipserverweb&u_video=${a_voip_info.upVideoToken(a_user_info.loginName,a_user_info.userCode)}" target="_blank">视频系统</a>

<#elseif ((a_user_info.manageCode?trim=="")||(a_user_info.manageCode?starts_with("HI140824100001")))>

	

	<a  class="btn" href="${b_method.upConfig('yeshospital.rtc_server')}?u_model=0&u_user=${a_user_info.loginName}&u_client=webbrowser" target="_blank">视频系统</a>

<#elseif (a_user_info.manageCode?starts_with("SI"))>
	<a  class="btn" href="${b_method.upConfig('yeshospital.rtc_server')}?u_model=1&u_user=${a_user_info.loginName}&u_client=manageclient" target="_blank">远程医疗系统</a>
</#if>





<div style="height:20px;"></div>



<@m_report_html_js e_themes=""/>

<script>
$(function () {
    $(document).ready(function () {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function () {

                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function () {
                            var x = (new Date()).getTime(), // current time
                                y = parseInt(Math.random()*100);
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            credits : { enabled:false//不显示highCharts版权信息 
	            },
            title: {
                text: '最新实时设备信息数据'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: '数量'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        this.y+'条';
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: '设备记录',
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -19; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: parseInt(Math.random()*100)
                        });
                    }
                    return data;
                }())
            }]
        });
    });
});
</script>
