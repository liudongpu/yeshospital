


<#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin p_type="index" p_css=["yesapp/css/mobile-index.css"] />

<@m_mobile_body_begin />





<div id="wrap">
    <header>
        <div id="cloud" class="topbar  activebar">
            首页
        </div>
        <div id="client" class="topbar  ">
            消息
        </div>
        <div id="dev" class="topbar ">
            发现
        </div>
        <div id="doc" class="topbar ">
            我的
        </div>
    </header>
    <div id="main">

    </div>
    <div id="footer">
        <ul>
            <li tapmode="active" class="active" onclick="randomSwitchBtn(this,'cloud',0)">
                <a class="bottom_btn cloud">首页</a>
            </li>

            <li tapmode="active" onclick="randomSwitchBtn(this,'client',1)">
                <a class="bottom_btn client ">消息</a>
            </li>

            <li tapmode="active" onclick="randomSwitchBtn(this,'dev',2)">
                <a class="bottom_btn dev ">发现</a>
            </li>

            <li tapmode="active" onclick="randomSwitchBtn(this,'doc',3)">
                <a class="bottom_btn doc ">我的</a>
            </li>
        </ul>
    </div>
</div>



<@m_mobile_body_end p_type="index" p_js=["yesapp/js/yesapp-index.js"] />
<@m_mobile_html_end />






