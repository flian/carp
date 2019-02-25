<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/x-icon" href="${rc.contextPath}/favicon.ico">
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <!--[if lt IE 9]>
    <!--
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/respond.min.js"></script>
    -->
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/huiadmin/v3.1.3/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/huiadmin/v3.1.3/admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/huiadmin/v3.1.3/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/huiadmin/v3.1.3/admin/skin/green/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/huiadmin/v3.1.3/admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>${carpConfig.projectName} - ${carpConfig.version}</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">${carpConfig.projectName} </a>
            <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="#">${carpConfig.projectName} </a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">${carpConfig.versionStatus}</span>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                <#-- 一级菜单 -->
                <#list menus as topMenu>
                    <li class="navbar-levelone"><a href="javascript:;">${topMenu.name}</a></li>
                </#list>
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li><a href="${rc.contextPath}/public/theme/change/0">Theme-AdminLTE</a></li>
                    <li><#include "layout/widget/currentUserName.ftl"/></li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">
                        <#include "layout/widget/currentUserName.ftl"/>
                            <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="${adminCtx}/logout">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-msg">
                        <a href="#" title="消息" class="TBD"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a>
                    </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
<#--图标-->
<#assign icons=["&#xe616;","&#xe613;","&#xe620;","&#xe622;","&#xe60d;","&#xe62d;","&#xe61a;","&#xe62e;","&#xe616;"]/>
<#-- 一级 -->
<#list menus as menu>
    <div class="menu_dropdown bk_2">
    <#-- 二级 -->
        <#list menu.children as subMenu>
            <dl>
                <dt><i class="Hui-iconfont">${icons[subMenu_index % (icons?size -1)]}</i> ${subMenu.name!''}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                    <#-- 三级 -->
                        <#list subMenu.children as leafMenu>
                            <li><a data-href="${adminCtx}${leafMenu.url!''}" data-title="${leafMenu.name!''}" href="javascript:void(0)">${leafMenu.name!''}</a></li>
                        </#list>
                    </ul>
                </dd>
            </dl>
        </#list>
    </div>
</#list>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="${adminCtx}/welcome">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="${adminCtx}/welcome"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前 </li>
        <li id="closeall">关闭全部 </li>
    </ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
    $(function(){
        $("body").Huitab({
            tabBar:".navbar-wrapper .navbar-levelone",
            tabCon:".Hui-aside .menu_dropdown",
            className:"current",
            index:0,
        });
        $(".TBD").on("click",function() {
            layer.alert("持续开发中。。。");
        })
    });
</script>

</body>
</html>