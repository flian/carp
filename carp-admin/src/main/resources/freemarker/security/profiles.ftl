<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>
        <el-button class="filter-item" style="margin-left: 10px;"
                   @click="profileModel={name:'',password:''};showCreateProfile=true"
                   type="primary" icon="edit">添加
        </el-button>

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
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="expand">
                <template scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="用户名">
                            <span> {{ props.row.userName }}</span>
                        </el-form-item>
                        <el-form-item label="角色">
                            <span> {{ props.row.roles }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="用户名" prop="userName"></el-table-column>
            <el-table-column label="角色" prop="roles"></el-table-column>

            <el-table-column align="center" label="操作">
                <template scope="scope">
                    <el-button size="small" type="success" @click="popChangeRole(scope.row)">分配角色
                    </el-button>
                    <el-button size="small" type="warning"
                               @click="changePasswordModel={password:'',name:scope.row.userName};showChangePassword=true">
                        改密
                    </el-button>
                    <el-button size="small" type="danger" @click="enable(scope.row.userName,true)" v-if="!scope.row.enable">启用
                    </el-button>
                    <el-button size="small" type="danger" @click="enable(scope.row.userName,false)" v-if="scope.row.enable">禁用
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="query.page" :page-sizes="[5, 10, 20, 40]" :page-size="query.size"
                       layout="total, sizes, prev, pager, next, jumper" :total="totalElements">
        </el-pagination>
        <el-dialog title="创建用户" :visible.sync="showCreateProfile">
            <el-form label-width="90px" ref="createProfileForm" :rules="createProfileFormRules" :model="profileModel">
                <el-form-item label="用户名" prop="name">
                    <el-input v-model="profileModel.name" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="profileModel.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-button @click="showCreateProfile = false">取消</el-button>
                <el-button @click="handleCreateProfile" type="primary">保存</el-button>
            </el-form>
        </el-dialog>

        <el-dialog title="修改密码" :visible.sync="showChangePassword">
            <el-form label-width="90px" ref="changePasswordForm" :rules="changePasswordFormRules"
                     :model="changePasswordModel">
                <el-form-item label="新密码" prop="password">
                    <el-input v-model="changePasswordModel.password" placeholder="请输入新密码" type="password"></el-input>
                </el-form-item>
                <el-button @click="showChangePassword=false">取消</el-button>
                <el-button @click="handleChangePassword" type="primary">保存</el-button>
            </el-form>
        </el-dialog>

        <!--分配角色-->
        <el-dialog title="分配用户角色" :visible.sync="showAssignRoleForm">
            <el-form>
                <el-form-item label="分配角色">
                    <el-checkbox-group v-model="assignedRoles">
                        <el-checkbox v-for="r in allRoles" :label="r.code">{{r.name}}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-button @click="showAssignRoleForm=false">取消</el-button>
                <el-button @click="handleChangeUserRole" type="primary">保存</el-button>
            </el-form>
        </el-dialog>
    </div>
</section>
</@layout.main>
<#macro myPageJS>
    <script>
        new Vue({
            el: '#app',
            computed: {},
            methods: {
                handleCreateProfile() {
                    var self = this;
                    self.$refs.createProfileForm.validate((valid) => {
                        if (valid) {
                            axios.post("${rc.contextPath}/profile", JSON.parse(JSON.stringify(self.profileModel)))
                                    .then(response => {
                                        self.showCreateProfile = false;
                                        self.queryItems();
                                    });
                        }
                    });
                },
                handleChangePassword() {
                    let self = this;
                    self.$refs.changePasswordForm.validate(
                            (valid) => {
                                if (valid) {
                                    axios.put("${rc.contextPath}/profile/" + self.changePasswordModel.name + "/password",
                                            { password: self.changePasswordModel.password })
                                            .then(response => {
                                                self.showChangePassword = false;
                                                self.queryItems();
                                            });
                                }
                            }
                    );
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
                popChangeRole(row) {
                    this.showAssignRoleForm = true;
                    this.currentRow = row;
                    this.assignedRoles = row.roleCodes;
                },
                enable(userName, enable) {
                    var self = this;
                    axios.put("${rc.contextPath}/profile/" + userName + "/enable", { "enable": enable })
                            .then(response => {
                                self.queryItems();
                                self.showAssignRoleForm = false;
                            })
                },
                handleChangeUserRole() {
                    var self = this;
                    axios.put("${rc.contextPath}/profile/roles", {
                        userId: self.currentRow.id,
                        roles: JSON.parse(JSON.stringify(this.assignedRoles))
                    })
                            .then(response => {
                                self.queryItems();
                                self.showAssignRoleForm = false;
                            })
                },
                queryItems() {
                    axios.get("${rc.contextPath}/profile/data", { params: this.query }).then(response => {
                        this.items = response.data.payload.content;
                        this.totalPage = response.data.payload.totalPages;
                        this.totalElements = response.data.payload.totalElements;
                    })
                },
                getAllRoles() {
                    axios.get("${rc.contextPath}/roles/all")
                            .then(response => {
                                this.allRoles = response.data.payload;
                            })
                }
            },
            created() {
                this.queryItems();
                this.getAllRoles();
            },
            data: function() {
                return {
                    query: {
                        page: 1,
                        size: 10,
                        keyword: ''
                    },
                    totalPage: 0,
                    totalElements: 0,
                    items: [],
                    selectedItems: [],
                    showAssignRoleForm: false,
                    currentRow: {},
                    allRoles: [],
                    userRoleCodes: [],
                    assignedRoles: [],
                    profileModel: {},
                    changePasswordModel: {},
                    showCreateProfile: false,
                    createProfileFormRules: {
                        name: [
                            { type: "string", required: true, message: "请输入用户名", trigger: "blur" },
                            { type: "string", min: 3, max: 20, message: "请输入3-20位用户名", trigger: "blur" }
                        ],
                        password: [
                            { type: "string", required: true, message: "请输入密码", trigger: "blur" },
                            { type: "string", min: 6, max: 20, message: "密码不符合规则", trigger: "blur" }
                        ]
                    },
                    changePasswordFormRules: {
                        password: [
                            { type: "string", required: true, message: "请输入新密码", trigger: "blur" },
                            { type: "string", min: 6, max: 20, message: "密码不符合规则", trigger: "blur" }
                        ]
                    },
                    showChangePassword: false
                }
            }
        })
    </script>

</#macro>