 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />
<#assign a_orderCode=RequestParameters['u_order_code']?default("") >
<#assign a_memberCode=RequestParameters['u_member_code']?default("") >

<#--如果用户编号为空 则开始根据code去取该code对应的用户的最近一张单据-->
<#if a_memberCode=="">

	<#assign a_postCard=RequestParameters['u_post_card']?default("") >

	<#if a_postCard!="">
	

	</#if>


</#if>


<@m_mobile_init_dbcall />

<#assign a_uid=a_macro_mobile_dbcall.dataGet("yh_tour_order_detail","uid","","tour_code",a_orderCode,"member_code",a_memberCode) >


<#if a_uid=="" >

<#assign b_page=b_method.upControlPage("page_add_m_yh_tour_order_detail","") />

<#else>
<#assign b_page=b_method.upControlPage("page_edit_m_yh_tour_order_detail","zw_f_uid="+a_uid) />
</#if>



<@m_mobile_header_begin p_title=b_page.getWebPage().getPageName() />
	<@m_mobile_button_back />
	<@m_zapmacro_mobile_top_operate   b_page.getWebPage().getPageOperate()  "116001016" />
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>
	
	
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_tm_order_code" e_value=a_orderCode />
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_tm_member_code" e_value=a_memberCode />
	
	<#assign a_member_info=b_method.upDataOne("yh_member_extend_geracomium","","","","member_code",a_memberCode) />
	
	${a_member_info["member_name"]}-年龄：${a_member_info["member_age"]}
	<div class="zmcss_h_20"></div>

	<#if a_uid=="" >	
		<@m_zapmacro_mobile_page_add b_page />	
	<#else>
		<@m_zapmacro_mobile_page_edit b_page />
	</#if>
	

	
	

	<div class="zmcss_h_20"></div>

			<div>
            	<a <@m_mobile_a_href p_page="member-info?u_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">个人信息</a>
            	<a <@m_mobile_a_href p_page="tour-drug?u_order_code=${a_orderCode}&u_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">购买药物</a>
            	<br/>
            	
            	
            </div>

	
	<div class="zmcss_h_20"></div>
	<div  class="yb_list_box">

		<ul id="yesapp_tm_ul_drug"  data-role="listview" data-split-icon="gear" data-inset="true">
		   
		</ul>
	</div>
	
	<div class="zmcss_h_20"></div>
	
	

 </div>
 
 

 <div data-role="popup" id="yesapp_tm_dialog_option" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1>操作</h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content" id="yesapp_tm_dialog_option_info">
       <a href="#" class="ui-btn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
       <a href="#" class="ui-btn">Anchor</a>
       
    </div>
</div>




 <div data-role="popup" id="yesapp_tm_dialog_t1" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1></h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content yb_list_dialog">
       <ol data-role="listview">
		    
		    <li onclick="yesapp_tour.tour_member_tmp_click(this)">未诉不适</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">护工述未见异常</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">诉偶尔头晕：头重脚轻、头胀、头昏、眼花、头晕目眩、失眠多梦</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">诉尿频、夜尿5次、排尿等待、排尿中断、排尿时间延长、尿痛   </li>                               
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">诉视物模糊</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">诉咳嗽</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">诉便秘</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">诉血压不稳</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">述腿痛</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">下肢浮肿</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">烦躁</li>
		    
		</ol>
       
    </div>
</div>

 <div data-role="popup" id="yesapp_tm_dialog_t2" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1></h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content yb_list_dialog">
       <ol data-role="listview">
			
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">密切观察</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">密切观察病情</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">完善检查、随诊</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">定期监测血糖、血压</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">注意保护眼睛、减少光线和灰尘的刺激</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">多饮水、必要时给予缓泻剂</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">限制钠摄入量，平卧下肢抬高位，如应用利尿剂最好放在上午，便于病人夜间休息</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">避免激动、保证病人足够的休息和睡眠、必要时酌情选用安抚剂</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">增强体质，预防感冒。戒烟</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">对症治疗</li>
			
			
		    
		</ol>
       
    </div>
</div>


<div data-role="popup" id="yesapp_tm_dialog_t3" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1></h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content yb_list_dialog">
       <ol data-role="listview">
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">全血细胞分析五分类</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">尿十一项</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">肝肾功能</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">肝功系列（含血糖）</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">肾功系列</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">血脂四项</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">贫血三项</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">肿瘤相关抗原</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">前列腺特异性抗原</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">生化全项</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">心肌酶谱</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">甲功5项</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">TB淋巴亚群</li>
			<li onclick="yesapp_tour.tour_member_tmp_click(this)">便常规+潜血</li>
		    
		    
		</ol>
       
    </div>
</div>
 


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
  <@m_mobile_html_initjs e_js="yesapp_tour.init_tour_member()" />
 <@m_mobile_html_end />






