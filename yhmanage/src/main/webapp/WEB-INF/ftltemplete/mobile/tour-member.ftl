 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />
<#assign a_orderCode=RequestParameters['order_code']?default("") >
<#assign a_memberCode=RequestParameters['member_code']?default("") >


<div data-role="header" style="overflow:hidden;">
<h1>填写信息</h1>
	<a <@m_mobile_a_back/> data-icon="back" class="ui-btn-left">返回</a>
    <a href="#" data-icon="plus" class="ui-btn-right">保存</a>

	
</div>




<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>
	
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_tm_order_code" e_value=a_orderCode />
	<@m_zapmacro_mobile_form_hidden e_id="yesapp_tm_member_code" e_value=a_memberCode />
	
	<#assign a_member_info=b_method.upDataOne("yh_member_extend_geracomium","","","","member_code",a_memberCode) />
	
	
	
	

	
	<form>
    <ul data-role="listview" data-inset="true">
        <li class="ui-field-contain">
            <label >姓名:</label>
            ${a_member_info["member_name"]}
        </li>
        <li class="ui-field-contain">
            <label for="textarea2">查房记录:</label>
        <textarea  rows="20" name="textarea2" id="textarea2"></textarea>
        </li>
       
    </ul>
	</form>

	<div class="zmcss_h_20"></div>

			<div>
            	<a href="#" class="ui-btn ui-btn-inline">个人信息</a>
            	<a <@m_mobile_a_href p_page="tour-drug?order_code=${a_orderCode}&member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">选择药物</a>
            	<br/>
            	
            	
            </div>

	
	<div class="zmcss_h_20"></div>
	<div>

		<ul id="yesapp_tm_ul_drug"  data-role="listview" data-split-icon="delete" data-inset="true">
		   
		</ul>
	</div>
	
	
	
	<div data-role="popup" id="purchase" data-theme="a" data-overlay-theme="b" class="ui-content" style="max-width:340px; padding-bottom:2em;">
    <h3>确认删除</h3>
<p>确认执行该操作吗？</p>
    <a href="index.html" data-rel="back" class="ui-shadow ui-btn ui-corner-all ui-btn-b ui-icon-check ui-btn-icon-left ui-btn-inline ui-mini">Buy: $10.99</a>
    <a href="index.html" data-rel="back" class="ui-shadow ui-btn ui-corner-all ui-btn-inline ui-mini">Cancel</a>
</div>

 </div>
 
 
 
 
 


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
  <@m_mobile_html_initjs e_js="yesapp_tour.init_tour_member()" />
 <@m_mobile_html_end />






