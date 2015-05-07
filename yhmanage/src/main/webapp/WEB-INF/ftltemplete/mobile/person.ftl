 <#include "../../macro/macro_mobile.ftl" />




<@m_mobile_html_begin />
<@m_mobile_body_begin />

<header class="bar bar-nav">
  <button class="btn btn-link btn-nav pull-left" <@m_mobile_back_root/> >
    <span class="icon icon-left-nav"></span>
    返回
  </button>
  <button class="btn btn-link btn-nav pull-right"  >
    保存
    
  </button>
  <h1 class="title">填写老人信息</h1>
</header>

 
<div>
<ul class="table-view">
  
  <li class="table-view-cell media">
    <a class="navigate-right"  href="#myModalexample" >
      <span class="media-object pull-left icon icon-info"></span>
      <div class="media-body">
        老人姓名<span class="pull-right">张三</span>
      </div>
    </a>
  </li>
  
</ul>

<div class="zmcss_w_96">

<textarea rows="5" placeholder="填写信息"></textarea>
</div>

<ul class="table-view">
  
  <li class="table-view-cell media">
    <a class="navigate-right"  href="#myModalexample" >
      <span class="media-object pull-left icon icon-info"></span>
      <div class="media-body">
        药品推荐<span class="pull-right">感冒药</span>
      </div>
    </a>
  </li>
  
</ul>
</div>

<div id="myModalexample" class="modal">
  <header class="bar bar-nav">
    <a class="icon icon-close pull-right" href="#myModalexample"></a>
    <h1 class="title">全部养老院</h1>
  </header>

  <div class="content">
    <div class="card">
  <ul class="table-view">
    <li class="table-view-cell">张三</li>
    <li class="table-view-cell">李四</li>
    <li class="table-view-cell table-view-divider">Divider</li>
    <li class="table-view-cell">Item 3</li>
    <li class="table-view-cell">Item 4</li>
  </ul>
</div>

  </div>
</div>
 

    
 <@m_mobile_body_end />
 <script>
 $(function(){
 
 var header = $api.byId('header');
        $api.fixIos7Bar(header);
        });
 </script>
 <@m_mobile_html_end />