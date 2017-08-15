<@layout.main pageJS=myPageJS>
    cards list page. vue & element UI test.
<div id="app">
    <el-button @click="visible = true">Button</el-button>
    <el-dialog v-model="visible" title="Hello world">
        <p>Try Element</p>
    </el-dialog>
</div>
</@layout.main>
<#macro myPageJS>
<script>
    new Vue({
        el: '#app',
        data: function() {
            return { visible: false }
        }
    })
</script>
</#macro>