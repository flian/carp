<#macro main  pageJS='' dashboard2=false title="">
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/webjars/bootstrap/css/bootstrap.css"/>
    <link href="${rc.contextPath}/huiadmin/v3.1.3/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="${rc.contextPath}/huiadmin/v3.1.3/admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="${rc.contextPath}/huiadmin/v3.1.3/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

    <!--[if IE 6]>
    <script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>${title}</title>
</head>
<body>
<div class="pd-20">
    <#nested />
</div>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/lib/jquery.validation/1.14.0/messages_zh.js"></script>-->
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/huiadmin/v3.1.3/admin/js/H-ui.admin.js"></script>
<!-- vue and element-ui-->
<script src="${rc.contextPath}/webjars/vue/dist/vue.js"></script>
<script src="${rc.contextPath}/webjars/axios/dist/axios.js"></script>
<script src="${rc.contextPath}/webjars/element-ui/lib/index.js"></script>
    <#if pageJS?is_directive>
        <@pageJS/>
    </#if>

<script type="text/javascript">
    // Add a response interceptor
    axios.interceptors.response.use(function (response) {
        // 全局业务异常提示
        if(response.procCode!=200 && response.data.procCode != 200){
            Vue.prototype.$message({
                showClose: true,
                message:  response.data.message,
                type: 'warning'
            });
        }
        return response;
    }, function (error) {
        // 全局错误异常提示
        Vue.prototype.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
        });
        return Promise.reject(error);
    });
</script>
</body>
</html>
</#macro>
