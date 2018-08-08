<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>
        <el-button class="filter-item" style="margin-left: 10px;"
                   @click="RoleModel={code:'',name:''};showCreateRole=true;" type="primary" icon="edit">添加</el-button>

        <el-collapse accordion>
            <el-collapse-item title="更多功能...">
                <div class="filter-container">
                    <el-input style="width: 200px;" class="filter-item" placeholder="标题">
                    </el-input>
                    <el-input style="width: 200px;" class="filter-item" placeholder="姓名">
                    </el-input>
                    <el-button class="filter-item" type="primary" icon="search" @click="">搜索</el-button>
                    <el-button class="filter-item" type="primary" icon="document" @click="">导出</el-button>
                    <el-checkbox class="filter-item">显示审核人</el-checkbox>
                </div>
            </el-collapse-item>
        </el-collapse>
        <el-table :data.sync="items" style="width: 100%" @selection-change="handleSelectionChange">
            <!-- 表头选择框
            <el-table-column type="selection" width="55"></el-table-column>
            -->
            <el-table-column type="expand">
                <template scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="ID">
                            <span> {{ props.row.id }}</span>
                        </el-form-item>
                        <el-form-item label="编码">
                            <span> {{ props.row.code }}</span>
                        </el-form-item>
                        <el-form-item label="名称">
                            <span> {{ props.row.name }}</span>
                        </el-form-item>
                        <el-form-item label="授予用户">
                            <span> {{ props.row.users }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="ID" prop="id"></el-table-column>
            <el-table-column label="编码" prop="code"></el-table-column>
            <el-table-column label="名称" prop="name"></el-table-column>
            <el-table-column label="授予用户" prop="users"></el-table-column>
            <el-table-column align="center" label="操作" >
                <template scope="scope">
                    <el-button size="small" type="success" @click="preAssignMenu(scope.row)" icon="el-icon-setting" title="分配菜单">
                    </el-button>
                    <el-button size="small" type="warning" @click="preAssignAction(scope.row)" icon="el-icon-edit-outline" title="分配功能">
                    </el-button>
                    <el-button size="small" type="danger" @click="">禁用
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="query.page" :page-sizes="[5, 10, 20, 40]" :page-size="query.size"
                       layout="total, sizes, prev, pager, next, jumper" :total="totalElements">
        </el-pagination>

        <el-dialog title="创建角色" :visible.sync="showCreateRole" >
            <el-form label-width="90px" ref="createRoleForm" :rules="createRoleFormRules" :model="RoleModel">
                <el-form-item label="角色编码" prop="code">
                    <el-input v-model="RoleModel.code" placeholder="请输入用角色编码"></el-input>
                </el-form-item>
                <el-form-item label="角色名称" prop="name">
                    <el-input v-model="RoleModel.name" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-button @click="showCreateRole = false">取消</el-button>
                <el-button @click="handleCreateRole"  type="primary">保存</el-button>
            </el-form>
        </el-dialog>

        <el-dialog title="分配角色菜单资源" :visible.sync="showAssignMenuForm">
            <el-form>
                <el-tabs type="border-card">
                    <el-tab-pane :label="firstLevelMenu.name" v-for="firstLevelMenu in resources.menuList">
                        <el-form-item  :label="secondLevelMenu.name" v-for="secondLevelMenu in firstLevelMenu.children">
                            <div style="margin: 0px 0;"></div>
                            <el-checkbox-group v-model="assignedMenus">
                                <el-checkbox :label="thirdLevelMenu.id"
                                             v-for="thirdLevelMenu in secondLevelMenu.children">
                                    {{thirdLevelMenu.name}}
                                </el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-tab-pane>
                </el-tabs>
                <el-button @click="showAssignMenuForm=false">取消</el-button>
                <el-button @click="handleSaveMenu"  type="primary">保存</el-button>
            </el-form>
        </el-dialog>

        <el-dialog title="分配角色功能" :visible.sync="showAssignActionForm">
            <el-form>
                <el-tabs type="border-card">
                    <el-tab-pane :label="firstLevelAction.name" v-for="firstLevelAction in resources.actionList">
                        <el-form-item :label="secondLevelAction.name" v-for="secondLevelAction in firstLevelAction.children">
                            <div style="margin: 0px 0;"></div>
                            <el-checkbox-group v-model="assignedActions" >
                                <el-checkbox :label="thirdLevelAction.id"
                                             v-for="thirdLevelAction in secondLevelAction.children">
                                    {{thirdLevelAction.name}}
                                </el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-tab-pane>
                </el-tabs>
                <el-button @click="showAssignActionForm=false">取消</el-button>
                <el-button @click="handleSaveAction"  type="primary">保存</el-button>
            </el-form>
        </el-dialog>
    </div>
</section>
</@layout.main>
<#macro myPageJS>
    <script>
        new Vue({
            el: '#app',
            computed: {
            },
            methods: {
                handleCreateRole(){
                    var self =this;
                    self.$refs.createRoleForm.validate((valid) =>{
                        if(valid){
                            axios.post("${rc.contextPath}/roles",JSON.parse(JSON.stringify(self.RoleModel)))
                                    .then(response=>{
                                        self.showCreateRole=false;
                                        self.queryItems();
                                    });
                        }
                    });
                },
                handleSizeChange(newSize) {
                    this.query.size = newSize;
                    this.queryItems();
                },
                handleCurrentChange(newPage) {
                    this.query.page = newPage;
                    this.queryItems();
                },
                handleSelectionChange(val) {
                    this.selectedItems = val;
                },
                handleSaveMenu(){
                    var self = this;
                    axios.put("${rc.contextPath}/roles/"+self.updateRoleId+"/menus", JSON.parse(JSON.stringify(self.assignedMenus)))
                            .then(response=>{
                                self.showAssignMenuForm = false;
                            });
                },
                handleSaveAction(){
                    var self = this;
                    axios.put("${rc.contextPath}/roles/"+self.updateRoleId+"/actions", JSON.parse(JSON.stringify(self.assignedActions)))
                            .then(response=>{
                                self.showAssignActionForm = false;
                            });
                },
                queryItems(){
                    axios.get("${rc.contextPath}/roles/data", {params: this.query}).then(response => {
                        console.log(response);
                        this.items = response.data.payload.content;
                        this.totalPage = response.data.payload.totalPages;
                        this.totalElements = response.data.payload.totalElements;
                    })
                },
                preAssignMenu(row){
                    this.updateRoleId = row.id;
                    this.queryResourcesByRoleCode(row.id);
                    this.showAssignMenuForm =true;
                },
                preAssignAction(row){
                    this.updateRoleId = row.id;
                    this.queryResourcesByRoleCode(row.id);
                    this.showAssignActionForm = true;
                },
                queryResourcesByRoleCode(roleId){
                    var self = this;
                    axios.get("${rc.contextPath}/roles/"+roleId+"/resources", {params: this.query})
                            .then(response =>{
                                self.roleResources = response.data.payload;
                                self.assignedMenus = self.roleResources.menuIdList;
                                self.assignedActions =  self.roleResources.actionIdList;
                            });
                },
                queryAllResources(){
                    var self = this;
                    axios.get("${rc.contextPath}/roles/resources", {params: this.query})
                            .then(response =>{
                                self.resources = response.data.payload;
                            });
                }
            },
            created(){
                this.queryItems();
                this.queryAllResources();
            },
            data: function () {
                return {
                    query: {
                        page: 1,
                        size: 10,
                        keyword:''
                    },
                    totalPage: 0,
                    totalElements: 0,
                    items: [],
                    selectedItems:[],
                    showAssignMenuForm:false,
                    showAssignActionForm:false,
                    updateRoleId:'',
                    assignedMenus:[],
                    assignedActions:[],
                    resources:{},
                    roleResources:{},
                    //创建角色
                    showCreateRole:false,
                    RoleModel:{},
                    createRoleFormRules:{
                        code:[
                            {type:"string",required:true,message:"请输入角色编码",trigger:"blur"},
                            {type:"string",min:6,max:20,message:"请输入6-20位角色名称",trigger:"blur"}
                        ],
                        name:[
                            {type:"string",required:true,message:"请输入角色名称",trigger:"blur"},
                            {type:"string",min:1,max:50,message:"请输入1-50位角色名称",trigger:"blur"}
                        ]
                    }
                }
            }
        })
    </script>
</#macro>