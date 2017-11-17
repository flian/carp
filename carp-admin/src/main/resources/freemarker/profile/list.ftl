<@layout.main pageJS=myPageJS>
<section class="content">
    <div id="app" v-cloak>
        <el-button class="filter-item" style="margin-left: 10px;" @click="" type="primary" icon="edit">添加</el-button>

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
                    <el-button size="small" type="warning" @click="">改密
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

        <el-dialog title="分配用户角色" :visible.sync="showAssignRoleForm">
            <el-form>
                <el-form-item label="分配角色">
                    <el-checkbox-group v-model="assignedRoles">
                            <el-checkbox v-for="r in allRoles" :label="r.code">{{r.name}}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-button @click="showAssignRoleForm=false">取消</el-button>
                <el-button @click="handleChangeUserRole"  type="primary">保存</el-button>
            </el-form>
        </el-dialog>
    </div>
</@layout.main>
<#macro myPageJS>
    <script>
        new Vue({
            el: '#app',
            computed: {
            },
            methods: {
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
                popChangeRole(row){
                    this.showAssignRoleForm = true;
                    this.currentRow = row;
                    this.assignedRoles = row.roleCodes;
                },
                handleChangeUserRole(){
                    var self = this;
                    axios.put("${rc.contextPath}/profile/roles",{userId: self.currentRow.id,roles:JSON.parse(JSON.stringify(this.assignedRoles))})
                            .then(response=>{
                                self.queryItems();
                                self.showAssignRoleForm = false;
                            })
                },
                queryItems(){
                    axios.get("${rc.contextPath}/profile/data", {params: this.query}).then(response => {
                        this.items = response.data.payload.content;
                        this.totalPage = response.data.payload.totalPages;
                        this.totalElements = response.data.payload.totalElements;
                    })
                },
                getAllRoles(){
                    axios.get("${rc.contextPath}/roles/all")
                            .then(response =>{
                                this.allRoles = response.data.payload;
                            })
                }
            },
            created(){
                this.queryItems();
                this.getAllRoles();
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
                    showAssignRoleForm:false,
                    currentRow:{},
                    allRoles:[],
                    userRoleCodes:[],
                    assignedRoles:[]
                }
            }
        })
    </script>
</section>
</#macro>