<@layout.main title="欢迎页" pageJS=myPageJS>
<p style="margin-left: 150px;margin-top:50px;font-size: 50px">欢迎使用${carpConfig.projectName} 系统!</p>
</@layout.main>
<#macro myPageJS>
<script type="text/javascript">
    console.log("hello welcome page!")
</script>
</#macro>