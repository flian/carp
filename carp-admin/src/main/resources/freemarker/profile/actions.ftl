<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>
        <el-tree :data="items" :props="defaultProps" node-key="id" default-expand-all
                 :expand-on-click-node="false"
                 :highlight-current="true"
                 :render-content="renderContent">
        </el-tree>
        <el-dialog :title="formTitle" :visible.sync="showForm">
            <el-form ref="actionForm" :rules="actionFormRules" :model="item" label-width="90px" label-position="right">
                <el-form-item label="ID" prop="id">
                    <el-input v-model="item.id" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="所属父类" prop="parentId">
                    <el-input v-model.number="item.parentId" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="名称" prop="name" >
                    <el-input v-model="item.name" :disabled="(showType==1)" placeholder="请填入名称"></el-input>
                </el-form-item>
                <el-form-item label="http请求" prop="actionMethod" >
                    <el-select v-model="item.actionMethod" :disabled="(showType==1)" placeholder="请选择">
                        <el-option v-for="option in actionMethodOptions"
                                   :key="option.value"
                                   :label="option.label"
                                   :value="option.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="URL" prop="actionUrl" >
                    <el-input v-model="item.actionUrl" :disabled="(showType==1)" placeholder="末级节点请填入url地址"></el-input>
                </el-form-item>
                <el-form-item label="显示顺序" prop="priority" >
                    <el-input v-model.number="item.priority" :disabled="(showType==1)" placeholder="请填入显示顺序"></el-input>
                </el-form-item>
                <el-form-item label="叶节点" prop="leaf">
                    <el-select v-model="item.leaf" placeholder="请选择" :disabled="(showType==1 || showType == 3)">
                        <el-option v-for="option in options" :key="option.value" :label="option.label" :value="option.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button @click="showForm=false">取消</el-button>
                    <el-button v-if="showType==1 && !item.root" @click="showType=3">编辑</el-button>
                    <el-button v-if="showType>1" type="primary" :disable=" showType==1" @click="save">保存</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</@layout.main>
<#macro myPageJS>
    <script>
        new Vue({
            el: '#app',
            computed: {
                formTitle: function () {
                    var self = this;
                    switch (self.showType) {
                        case 1: {
                            return "菜单详情";
                        }
                        case 2: {
                            return "新增菜单";
                        }
                        case 3: {
                            return "编辑菜单";
                        }
                    }
                }
            },
            created: function () {
                this.queryItems();
            },
            data: {
                items: [],
                item: {},
                showForm: false,
                showType: 1,//1:查看详情，2:新增，3：编辑,
                storeRef: {},
                dataRef: {},
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                labelPosition: 'left',
                options:[{value:true,label:"叶节点"},{value:false,label:"非叶子节点"}],
                actionMethodOptions:[
                    {value:"ALL",label:"ALL"},
                    {value:"POST",label:"POST"},
                    {value:"GET",label:"GET"},
                    {value:"PUT",label:"PUT"},
                    {value:"DELETE",label:"DELETE"}
                    ],
                actionFormRules:{
                    id:[
                        {type:"string",required:true,message:"必填字段",trigger:"blur"}
                    ],
                    parentId:[
                        {type:"integer",required:true,message:"必填字段",trigger:"blur"}
                    ],
                    name:[
                        {type:"string",required:true,message:"必填字段",trigger:"blur"}
                    ],
                    actionMethod:[
                        {type:"string",required:true,message:"必填字段",trigger:"blur"}
                    ],
                    actionUrl:[
                        {type:"string",required:true,message:"必填字段",trigger:"blur"}
                    ],
                    priority:[
                        {type:"integer",required:true,message:"必填字段",trigger:"blur"}
                    ],
                    leaf:[
                        {type:"boolean",required:true,message:"请选择",trigger:"blur"}
                    ]
                }
            },
            methods: {
                save: function () {
                    var self = this;
                    self.$refs.actionForm.validate((valid) => {
                        if(valid){
                            switch (self.showType) {
                                case 1: {
                                    //详情
                                    break;
                                }
                                case 2: {
                                    //新增
                                    self.storeRef.append(JSON.parse(JSON.stringify(self.item)), self.dataRef);
                                    break;
                                }
                                case 3: {
                                    //编辑
                                    break;
                                }
                            }
                            self.showForm = false;
                        }else{
                            console.log("error submit!");
                            return false;
                        }
                    });
                },
                remove: function (store, data) {
                    store.remove(data);
                },
            <#-- ref : http://blog.csdn.net/x_lord/article/details/70161195 -->
                renderContent: function (createElement, {node, data, store}) {
                    var self = this;
                    return createElement('span', [
                        createElement('span', node.label),
                        createElement('span', {
                            attrs: {
                                style: "float: right; margin-right: 20px"
                            }
                        }, [
                            createElement('el-button', {
                                attrs: {
                                    size: "mini", type: "success"
                                }, on: {
                                    click: function () {
                                        self.showType = 1;
                                        self.storeRef = store;
                                        self.dataRef = data;
                                        self.item = data;
                                        self.showForm = true;
                                    }
                                }
                            }, "详情"),
                            createElement('el-button', {
                                attrs: {
                                    size: "mini", type: "warning",
                                    disabled: (data.leaf)
                                }, on: {
                                    click: function () {
                                        self.showType = 2;
                                        self.storeRef = store;
                                        self.dataRef = data;
                                        self.item = {
                                            id: "-9999",
                                            parentId: data.id,
                                            name:"",
                                            children:[],
                                            actionMethod:"ALL",
                                            actionUrl:"TBD",
                                            leaf:true
                                        }
                                        self.showForm = true;
                                    }
                                }
                            }, "添加子节点"),
                            createElement('el-button', {
                                attrs: {
                                    size: "mini", type: "danger",
                                    disabled: (data.children.length > 0 || node.data.root)
                                }, on: {
                                    click: function () {

                                        self.$confirm('永久删除选中行?', '确认删除', {
                                            confirmButtonText: '确认删除',
                                            cancelButtonText: '取消',
                                            type: 'warning'
                                        }).then(() => {
                                            //TODO 后台删除
                                            self.remove(store, data);
                                        }).catch(()=>{
                                            console.log("取消删除")
                                        });
                                    }
                                }
                            }, "删除"),
                        ]),
                    ]);
                },
                queryItems: function () {
                    var self = this;
                    axios.get("${rc.contextPath}/actions/data").then(response => {
                        self.items = response.data.payload;
                    })
                }
            }
        })
    </script>
</section>
</#macro>