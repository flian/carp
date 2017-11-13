<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>


                <el-tree :data="items" :props="defaultProps" node-key="id" default-expand-all="true"
                 :render-content="renderContent">
                </el-tree>
<#-- ref : http://blog.csdn.net/x_lord/article/details/70161195 -->
            <#--
                <el-form ref="form" :model="item" label-width="80px" label-position="right">
                    <el-form-item label="ID">
                        <el-input v-model="item.id"></el-input>
                    </el-form-item>
                    <el-form-item label="所属父类">
                        <el-input v-model="item.parentId"></el-input>
                    </el-form-item>
                    <el-form-item label="名称">
                        <el-input v-model="item.name"></el-input>
                    </el-form-item>
                    <el-form-item label="URL">
                        <el-input v-model="item.url"></el-input>
                    </el-form-item>
                    <el-form-item label="显示顺序">
                        <el-input v-model="item.priority"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary">立即保存</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            -->



    </div>
</@layout.main>
<#macro myPageJS>
    <script>
        new Vue({
            el: '#app',
            computed: {
            },
            methods: {
                append:function(store, data) {
                    store.append({ key: "1212", name: '新节点', children: [] }, data);
                },
                remove:function(store, data) {
                    store.remove(data);
                },
                renderContent:function(createElement, { node, data, store }) {
                    var self = this;
                    return createElement('span', [
                        createElement('span', node.label),
                        createElement('span', {attrs:{
                            style:"float: right; margin-right: 20px"
                        }},[
                            createElement('el-button',{attrs:{
                                size:"mini",type:"success"
                            },on:{
                                click:function() {

                                }
                            }},"详情"),
                            createElement('el-button',{attrs:{
                                size:"mini",type:"warning"
                            },on:{
                                click:function() {

                                }
                            }},"添加子节点"),
                            createElement('el-button',{attrs:{
                                size:"mini",type:"danger"
                            },on:{
                                click:function() {

                                }
                            }},"删除"),
                        ]),
                    ]);
                },
                    queryItems(){
                    var self = this;
                    axios.get("${rc.contextPath}/menus/data").then(response =>{
                        self.items = response.data.payload;
                    })
                }
            },
            created(){
                this.queryItems();
            },
            data: function () {
                return {
                    items:[],
                    item:{},
                    defaultProps: {
                        children: 'children',
                        label: 'name'
                    },
                    labelPosition: 'left'
                }
            }
        })
    </script>
</section>
</#macro>