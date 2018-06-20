<#global adminCtx="${rc.contextPath}"/>
<#if carpConfig.huiAdminTheme>
    <#include "huiAdminMainLayout.ftl"/>
    <#else >
        <#include "mainLayout.ftl"/>
</#if>

