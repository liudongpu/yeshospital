 <#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin />
<@m_mobile_body_begin />
<div class="">
	<div class="zmcss_h_10"></div>
	<div class="zmcss_w_96 ">
	 <button class="btn  btn-positive zmcss_f_right" <@m_mobile_call_page p_page="../mb/page_add_m_yh_tour_order_info" />>
  		<span class="icon icon-plus"></span>
  		创建一个巡视
	</button>
	</div>
	<div class="zmcss_h_10"></div>
	
	<ul class="table-view">
  <li class="table-view-cell media"  <@m_mobile_call_page p_page="../tour/person" />>
    <a class="navigate-right">
      
      <div  class="media-object pull-left" >
      
      <button class="btn btn-negative btn-block">&nbsp;进行中&nbsp;</button>
      </div>
      
      <div class="media-body">
        2015-05-01
        <p>
        北京丰台区康助护养院<br/>
        已检查：54人 未检查：42人
        </p>
      </div>
    </a>
  </li>
  <li class="table-view-cell media" <@m_mobile_call_page p_page="../tour/list" />>
    <a class="navigate-right">
       
      <div  class="media-object pull-left" >
      
      <button class="btn btn-primary btn-block btn-outlined">&nbsp;已完成&nbsp;</button>
      </div>
      <div class="media-body">
        2015-04-13
        <p>
        北京市海淀区羊坊店敬老院<br/>
        已检查：11人 未检查：14人
        </p>
      </div>
    </a>
  </li>
  <li class="table-view-cell media" <@m_mobile_call_page p_page="../tour/list" />>
    <a class="navigate-right">
      <div  class="media-object pull-left" >
      
      <button class="btn btn-primary btn-block btn-outlined">&nbsp;已完成&nbsp;</button>
      </div>
      <div class="media-body">
        2015-05-01
        <p>
        房山区红叶老年公寓<br/>
        已检查：5人 未检查：4人
        </p>
      </div>
    </a>
  </li>
</ul>
	
	
	

</div>
 
 

    
 <@m_mobile_body_end />
 <@m_mobile_html_end />