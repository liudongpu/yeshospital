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
	
	<@m_zapmacro_mobile_html_hidden e_id="yesapp_tm_order_code" e_value=a_orderCode />
	<@m_zapmacro_mobile_html_hidden e_id="yesapp_tm_member_code" e_value=a_memberCode />
	
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
        <li class="ui-field-contain">
            
            <div>
            	<a href="#" class="ui-btn ui-btn-inline">个人信息</a>
            	<a <@m_mobile_a_href p_page="tour-drug?order_code=${a_orderCode}&member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">选择药物</a>
            	<br/>
            	<table data-role="table" id="table-custom-2" class="ui-body-d ui-shadow table-stripe ui-responsive" >
         <thead>
           <tr class="ui-bar-d">
             <th data-priority="2">序号</th>
             <th>名称</th>
             <th data-priority="3">操作</th>
            
           </tr>
         </thead>
         <tbody>
           <tr>
             <th>1</th>
             <td><a href="http://en.wikipedia.org/wiki/Citizen_Kane" data-rel="external">Citizen Kane</a></td>
             <td>1941</td>
            
           </tr>
           <tr>
             <th>2</th>
             <td><a href="http://en.wikipedia.org/wiki/Casablanca_(film)" data-rel="external">Casablanca</a></td>
             <td>1942</td>
             
           </tr>
           <tr>
             <th>3</th>
             <td><a href="http://en.wikipedia.org/wiki/The_Godfather" data-rel="external">The Godfather</a></td>
             <td>1972</td>
             
           </tr>
           <tr>
             <th>4</th>
             <td><a href="http://en.wikipedia.org/wiki/Gone_with_the_Wind_(film)" data-rel="external">Gone with the Wind</a></td>
             <td>1939</td>
             
           </tr>
           <tr>
             <th>5</th>
             <td><a href="http://en.wikipedia.org/wiki/Lawrence_of_Arabia_(film)" data-rel="external">Lawrence of Arabia</a></td>
             <td>1962</td>
             
           </tr>
           <tr>
             <th>6</th>
             <td><a href="http://en.wikipedia.org/wiki/Dr._Strangelove" data-rel="external">Dr. Strangelove Or How I Learned to Stop Worrying and Love the Bomb</a></td>
             <td>1964</td>
             
           </tr>
           
         </tbody>
       </table>
            	
            </div>
        </li>
    </ul>
	</form>
	
	
	<div>

		
	</div>

 </div>
 
 
 
 
 


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
 
 <@m_mobile_html_end />






