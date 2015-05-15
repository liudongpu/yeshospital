 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />


<div data-role="header" style="overflow:hidden;">
	<h1>选择药物</h1>
	<a <@m_mobile_a_back/> data-icon="back" class="ui-btn-left">返回</a>
    <a href="#" data-icon="plus" class="ui-btn-right">保存</a>

	
</div>




<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>

	<input id="yesapp_ts_search" type="search" placeholder="请输入药物名" onkeyup="yesapp_tour.tour_drug_search(this)"/>
	
	  <ul  data-role="listview" data-inset="true"  id="yesapp_ts_table" >
	    
	  </ul>
	
	
	
	<div class="zmcss_h_20"></div>
	<div id="yesapp_ts_select">
	
		<form>
    	<ul data-role="listview" data-inset="true">
        <li class="ui-field-contain">
            <label >名称:</label>
            <input name="name2" id="name2" value="" data-clear-btn="true" type="text">
        </li>
        <li class="ui-field-contain">
            <label for="textarea2">厂家:</label>
        	<input name="name2" id="name2" value="" data-clear-btn="true" type="text">
        </li>
        <li class="ui-field-contain">
            <label for="textarea2">用法用量:</label>
        <textarea  rows="20" name="textarea2" id="textarea2"></textarea>
        </li>
        <li class="ui-field-contain">
            <label for="flip2">是否购药:</label>
            <select name="flip2" id="flip2" data-role="slider">
                <option value="0"></option>
                <option value="1">是</option>
            </select>
        </li>
        <li class="ui-field-contain">
            <label for="textarea2">数量:</label>
        	<input data-clear-btn="false" name="number-1" id="number-1" value="" type="number"/>
        </li>
        </ul>
        </form>
	
	</div>


 </div>


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
 
 <@m_mobile_html_end />






