<#if Session.SPRING_SECURITY_CONTEXT?? && Session.SPRING_SECURITY_CONTEXT.authentication??>
    ${Session.SPRING_SECURITY_CONTEXT.authentication.principal.username!'Anonymous'}
<#else >
<script type="text/javascript">
    window.location.href ="${rc.contextPath}/login";
</script>
</#if>
